package ua.edu.lp.reliability.model.event;

import java.util.List;

import ua.edu.lp.reliability.model.issue.Issue;

public class JiraIssueImportEvent extends ApplicationEvent<List<Issue>> {

	private static final long serialVersionUID = 1L;

	public JiraIssueImportEvent(List<Issue> issues) {
		super(issues);
	}
}
