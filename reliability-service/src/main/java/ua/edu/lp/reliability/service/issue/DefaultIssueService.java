package ua.edu.lp.reliability.service.issue;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import ua.edu.lp.reliability.dao.issue.IssueDAO;
import ua.edu.lp.reliability.model.issue.Issue;

@Service("issueService")
public class DefaultIssueService implements IssueService {

	private static final Logger LOG = LoggerFactory.getLogger(DefaultIssueService.class);

	@Resource(name = "issueDao")
	private IssueDAO issueDao;

	@Override
	public List<Issue> getAll() {
		return issueDao.getAll();
	}

	@Override
	public void createIssue(Issue issue) {
		LOG.info("Create issue: " + issue);
		issueDao.create(issue);
	}
}
