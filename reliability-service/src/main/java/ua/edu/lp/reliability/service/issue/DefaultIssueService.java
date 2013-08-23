package ua.edu.lp.reliability.service.issue;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.edu.lp.reliability.dao.issue.IssueDAO;
import ua.edu.lp.reliability.model.issue.Issue;

@Service
public class DefaultIssueService implements IssueService {

	@Autowired
	private IssueDAO issueDao;

	@Override
	public List<Issue> getAll() {
		return issueDao.getAll();
	}

}
