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

import ua.edu.lp.reliability.jira.utils.JiraUtils;
import ua.edu.lp.reliability.model.issue.Issue;
import ua.edu.lp.reliability.model.issue.IssuePriority;
import ua.edu.lp.reliability.model.issue.IssueType;
import ua.edu.lp.reliability.model.project.Project;
import ua.edu.lp.reliability.model.user.User;
import ua.edu.lp.reliability.utils.Callback;

import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.api.JiraRestClientFactory;
import com.atlassian.jira.rest.client.api.domain.SearchResult;
import com.atlassian.jira.rest.client.internal.async.AsynchronousJiraRestClientFactory;

@Service("jiraService")
public class DefaultJiraService implements JiraService {

	private static final Logger LOG = LoggerFactory.getLogger(DefaultJiraService.class);

	// TODO: Should be moved to settings for each project
	private static final String USER_NAME = "pufik536";
	private static final String PASSWORD = "Nata_19871016";
	private static final String JIRA_HOME_URL = "https://issues.apache.org/jira";

	@Resource(name = "jiraIssueImportCallback")
	private Callback<List<Issue>> issueImportCallback;

	@Override
	public List<Issue> getIssueByProject(Project project) {
		List<Issue> issues = new ArrayList<>();
		try {
			final JiraRestClient restClient = getJiraRestClient();
			int startAt = 0;
			int maxResults = 50;

			SearchResult searchResult = requestIssues(restClient, startAt, maxResults);
			int issueCount = searchResult.getTotal();

			while (issueCount > startAt) {
				searchResult = requestIssues(restClient, startAt, maxResults);
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
		try {
			final JiraRestClient restClient = getJiraRestClient();
			int startAt = 0;
			int maxResults = 50;

			SearchResult searchResult = requestIssues(restClient, startAt, maxResults);
			int issueCount = searchResult.getTotal();

			while (issueCount > startAt) {
				searchResult = requestIssues(restClient, startAt, maxResults);
				startAt = searchResult.getMaxResults() + searchResult.getStartIndex();

				processIssue(searchResult.getIssues(), project);
			}

			restClient.close();
		} catch (URISyntaxException e) {
			LOG.error("Error while parsing URI", e);
		} catch (InterruptedException e) {
			LOG.error("Error while execution request to jira", e);
		} catch (ExecutionException e) {
			LOG.error("Error while execution request to jira", e);
		} catch (IOException e) {
			LOG.error("Can't close Jira rest client", e);
		} catch (Exception e) {
			LOG.error("Error while processing Jira response", e);
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

	private SearchResult requestIssues(final JiraRestClient restClient, int startAt, int maxResults) throws InterruptedException, ExecutionException {
		SearchResult searchResult;
		searchResult = restClient.getSearchClient().searchJql("project=JCR", maxResults, startAt, null).get();
		return searchResult;
	}

	private JiraRestClient getJiraRestClient() throws URISyntaxException {
		URI jiraServerUri = new URI(JIRA_HOME_URL);
		JiraRestClientFactory restClientFactory = new AsynchronousJiraRestClientFactory();
		final JiraRestClient restClient = restClientFactory.createWithBasicHttpAuthentication(jiraServerUri, USER_NAME, PASSWORD);
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
