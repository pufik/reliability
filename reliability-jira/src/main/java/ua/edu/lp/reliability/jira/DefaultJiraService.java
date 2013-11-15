package ua.edu.lp.reliability.jira;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import ua.edu.lp.reliability.jira.utils.JiraUtils;
import ua.edu.lp.reliability.model.issue.Issue;
import ua.edu.lp.reliability.model.issue.IssuePriority;
import ua.edu.lp.reliability.model.jira.JiraSettings;
import ua.edu.lp.reliability.model.project.Project;
import ua.edu.lp.reliability.model.user.User;
import ua.edu.lp.reliability.utils.Callback;

import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.api.JiraRestClientFactory;
import com.atlassian.jira.rest.client.api.domain.SearchResult;
import com.atlassian.jira.rest.client.internal.async.AsynchronousJiraRestClientFactory;

@Service("jiraService")
public class DefaultJiraService implements JiraService {

	private static final String PROJECT_JQL = "project=";

	private static final Logger LOG = LoggerFactory.getLogger(DefaultJiraService.class);

	@Resource(name = "jiraIssueImportCallback")
	private Callback<List<Issue>> issueImportCallback;

	@Override
	public List<Issue> getIssueByProject(Project project) {
		List<Issue> issues = new ArrayList<>();
		JiraSettings jiraSettings = project.getJiraSettings();
		Assert.notNull(jiraSettings, "Jira settings for project can't be empty, project: " + project);

		try {
			final JiraRestClient restClient = getJiraRestClient(jiraSettings);
			int startAt = 0;
			int maxResults = 50;

			SearchResult searchResult = requestIssues(restClient, jiraSettings.getProjectUid(), startAt, maxResults);
			int issueCount = searchResult.getTotal();

			while (issueCount > startAt) {
				searchResult = requestIssues(restClient, jiraSettings.getProjectUid(), startAt, maxResults);
				startAt = searchResult.getMaxResults() + searchResult.getStartIndex();

				for (com.atlassian.jira.rest.client.api.domain.Issue issue : searchResult.getIssues()) {
					issues.add(convertIssue(issue, project));
				}

				processIssue(searchResult.getIssues(), project);

			}
		} catch (URISyntaxException e) {
			LOG.error("Error while parsing URI", e);
		} catch (InterruptedException e) {
			LOG.error("Error while execution request to jira", e);
		} catch (ExecutionException e) {
			LOG.error("Error while execution request to jira", e);
		}

		return issues;
	}

	@Override
	@Async
	public void importIssueForProject(Project project) {
		JiraRestClient restClient = null;
		try {
			JiraSettings jiraSettings = project.getJiraSettings();
			Assert.notNull(jiraSettings, "Jira settings for project can't be empty, project: " + project);

			restClient = getJiraRestClient(jiraSettings);
			int startAt = 0;
			int maxResults = 50;

			SearchResult searchResult = requestIssues(restClient, jiraSettings.getProjectUid(), startAt, maxResults);
			int issueCount = searchResult.getTotal();

			while (issueCount > startAt) {
				searchResult = requestIssues(restClient, jiraSettings.getProjectUid(), startAt, maxResults);
				startAt = searchResult.getMaxResults() + searchResult.getStartIndex();

				processIssue(searchResult.getIssues(), project);
			}
		} catch (URISyntaxException e) {
			LOG.error("Error while parsing URI", e);
		} catch (InterruptedException e) {
			LOG.error("Error while execution request to jira", e);
		} catch (ExecutionException e) {
			LOG.error("Error while execution request to jira", e);
		} catch (Exception e) {
			LOG.error("Error while processing Jira response", e);
		} finally {
			try {
				restClient.close();
			} catch (IOException e) {
				LOG.error("Can't close Jira rest client", e);
			}
		}
	}

	public void processIssue(Iterable<com.atlassian.jira.rest.client.api.domain.Issue> jiraIssues, Project project) {
		List<Issue> issues = new ArrayList<>();

		for (com.atlassian.jira.rest.client.api.domain.Issue issue : jiraIssues) {
			issues.add(convertIssue(issue, project));
		}

		// Do async callback for issue processing
		issueImportCallback.execute(issues);

	}

	private SearchResult requestIssues(final JiraRestClient restClient, String projectUID, int startAt, int maxResults) throws InterruptedException,
			ExecutionException {
		SearchResult searchResult;
		searchResult = restClient.getSearchClient().searchJql(PROJECT_JQL + projectUID, maxResults, startAt, null).get();
		return searchResult;
	}

	private JiraRestClient getJiraRestClient(JiraSettings settings) throws URISyntaxException {
		URI jiraServerUri = new URI(settings.getUrl());
		JiraRestClientFactory restClientFactory = new AsynchronousJiraRestClientFactory();
		final JiraRestClient restClient = restClientFactory.createWithBasicHttpAuthentication(jiraServerUri, settings.getUserName(), settings.getPassword());
		return restClient;
	}

	private Issue convertIssue(com.atlassian.jira.rest.client.api.domain.Issue jiraIssue, Project project) {
		Issue issue = new Issue();
		issue.setKey(jiraIssue.getKey());
		issue.setProject(project);
		issue.setCreateDate(jiraIssue.getCreationDate().toDate());
		issue.setSummary(jiraIssue.getDescription());

		if (jiraIssue.getReporter() != null) {
			User reporter = new User();
			reporter.setFullname(jiraIssue.getReporter().getDisplayName());
			reporter.setLogin(jiraIssue.getReporter().getName());
			issue.setReporter(reporter);
		}

		if (jiraIssue.getPriority() != null) {
			issue.setPriority(IssuePriority.valueOf(jiraIssue.getPriority().getName().toUpperCase()));
		}

		issue.setType(JiraUtils.getIssueTypeByJiraIssue(jiraIssue));

		return issue;
	}
}
