package ua.edu.lp.reliability.jira;

import java.util.Date;
import java.util.List;

import ua.edu.lp.reliability.model.issue.Issue;
import ua.edu.lp.reliability.model.project.Project;

public class StandaloneApplication {
	private static JiraService jiraService;

	static {
		jiraService = new DefaultJiraService();
	}

	public static void main(String[] args) {
		System.out.println("Start JIRA integration...");
		System.out.println("start time: " + (new Date()).toString());

		Project project = null;
		List<Issue> issues = jiraService.getIssueByProject(project);

		System.out.println("------------------------------------");
		System.out.println("End time: " + (new Date()).toString());
		System.out.println("JIRA response: ");
		System.out.println("Issue count: " + issues.size());
		System.out.println(issues);

	}

}
