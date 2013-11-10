package ua.edu.lp.reliability.service.issue;

import java.util.List;

import ua.edu.lp.reliability.model.issue.Issue;

public interface IssueService {

	List<Issue> getAll();
	
	void createIssue(Issue issue);
}
