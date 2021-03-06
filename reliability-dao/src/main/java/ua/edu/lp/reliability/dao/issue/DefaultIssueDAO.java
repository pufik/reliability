package ua.edu.lp.reliability.dao.issue;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Repository;

import ua.edu.lp.reliability.dao.AbstractBaseDAO;
import ua.edu.lp.reliability.model.issue.Issue;

@Repository("issueDao")
public class DefaultIssueDAO extends AbstractBaseDAO<Issue, Long> implements IssueDAO {

	private static final String ISSUE_GET_ALL = "Issue.getAll";

	@Override
	protected Class<Issue> getEntityClass() {
		return Issue.class;
	}

	@Override
	public List<Issue> getAll() {
		return executeNamedQuery(ISSUE_GET_ALL, Collections.<String, Object> emptyMap());
	}
}
