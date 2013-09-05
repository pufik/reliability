package ua.edu.lp.reliability.service.issue;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import ua.edu.lp.reliability.dao.issue.IssueDAO;
import ua.edu.lp.reliability.model.issue.Issue;

@Service("issueService")
public class DefaultIssueService implements IssueService {

	@Resource(name = "issueDao")
	private IssueDAO issueDao;

	@Override
	public List<Issue> getAll() {
		return issueDao.getAll();
	}

}
