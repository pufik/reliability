package ua.edu.lp.reliability.jira;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ua.edu.lp.reliability.model.issue.Issue;
import ua.edu.lp.reliability.model.project.Project;
import ua.edu.lp.reliability.model.user.User;

import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.api.JiraRestClientFactory;
import com.atlassian.jira.rest.client.api.domain.SearchResult;
import com.atlassian.jira.rest.client.internal.async.AsynchronousJiraRestClientFactory;

public class DefaultJiraService implements JiraService {

	private static final String USER_NAME = "username";
	private static final String PASSWORD = "password";
	private static final String JIRA_HOME_URL = "https://issues.apache.org/jira";
	private static final Logger LOG = LoggerFactory.getLogger(DefaultJiraService.class);

	@Override
	// TODO: Should be changed to more clear implementation
	public List<Issue> getIssueByProject(Project project) {
		List<Issue> issues = new ArrayList<>();
		try {
			URI jiraServerUri = new URI(JIRA_HOME_URL);
			JiraRestClientFactory restClientFactory = new AsynchronousJiraRestClientFactory();
			final JiraRestClient restClient = restClientFactory.createWithBasicHttpAuthentication(jiraServerUri, USER_NAME, PASSWORD);

			SearchResult searchResult = restClient.getSearchClient().searchJql("project=JCR").get();
			int i = 0;
			int startAt = 0;
			int maxResults = 50;
			int issueCount = searchResult.getTotal();

			while (issueCount > i) {
				startAt = searchResult.getMaxResults() + searchResult.getStartIndex();
				for (com.atlassian.jira.rest.client.api.domain.Issue issue : searchResult.getIssues()) {
					issues.add(convertIssue(issue));
					i++;
				}

				searchResult = restClient.getSearchClient().searchJql("project=JCR", maxResults, startAt, null).get();
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

	private Issue convertIssue(com.atlassian.jira.rest.client.api.domain.Issue jiraIssue) {
		Issue issue = new Issue();
		issue.setKey(jiraIssue.getKey());
		issue.setCreateDate(jiraIssue.getCreationDate().toDate());
		issue.setSummary(jiraIssue.getDescription());

		if (jiraIssue.getAssignee() != null) {
			User assignee = new User();
			assignee.setFullname(jiraIssue.getAssignee().getDisplayName());
			assignee.setLogin(jiraIssue.getAssignee().getName());
			issue.setAssignee(assignee);
		}

		if (jiraIssue.getReporter() != null) {
			User reporter = new User();
			reporter.setFullname(jiraIssue.getReporter().getDisplayName());
			reporter.setLogin(jiraIssue.getReporter().getName());
			issue.setReporter(reporter);
		}

		// jiraIssue.getPriority().getName();
		// jiraIssue.getIssueType().getName();

		System.out.println(issue);

		return issue;
	}

}
