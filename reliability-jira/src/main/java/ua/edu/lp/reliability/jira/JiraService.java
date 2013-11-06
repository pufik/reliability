package ua.edu.lp.reliability.jira;

import java.util.List;

import ua.edu.lp.reliability.model.issue.Issue;
import ua.edu.lp.reliability.model.project.Project;

public interface JiraService {

	List<Issue> getIssueByProject(Project project);

}
