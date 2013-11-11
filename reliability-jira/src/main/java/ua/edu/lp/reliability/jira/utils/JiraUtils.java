package ua.edu.lp.reliability.jira.utils;

import java.util.HashMap;
import java.util.Map;

import com.atlassian.jira.rest.client.api.domain.Issue;

import ua.edu.lp.reliability.model.issue.IssueType;

public class JiraUtils {

	private static final Map<String, IssueType> jiraIssueTypesMapping;
	static {
		jiraIssueTypesMapping = new HashMap<>();
		jiraIssueTypesMapping.put(IssueType.BUG.getDescription(), IssueType.BUG);
		jiraIssueTypesMapping.put(IssueType.FEATURE.getDescription(), IssueType.FEATURE);
		jiraIssueTypesMapping.put(IssueType.IMPROVEMENT.getDescription(), IssueType.IMPROVEMENT);
		jiraIssueTypesMapping.put(IssueType.SUBTASK.getDescription(), IssueType.SUBTASK);
		jiraIssueTypesMapping.put(IssueType.TASK.getDescription(), IssueType.TASK);
		jiraIssueTypesMapping.put(IssueType.TEST.getDescription(), IssueType.TEST);
		jiraIssueTypesMapping.put(IssueType.WISH.getDescription(), IssueType.WISH);
	}

	public static IssueType getIssueTypeByJiraIssue(Issue issue) {
		IssueType issueType = null;
		
		if (issue.getIssueType() != null) {
			issueType = jiraIssueTypesMapping.get(issue.getIssueType().getName());
		}
		
		return issueType;
	}
}
