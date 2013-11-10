package ua.edu.lp.reliability.service.issue;

import java.util.List;

import javax.annotation.Resource;
import javax.persistence.NoResultException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import ua.edu.lp.reliability.model.issue.Issue;
import ua.edu.lp.reliability.model.user.User;
import ua.edu.lp.reliability.service.user.UserService;
import ua.edu.lp.reliability.utils.Callback;

@Component("jiraIssueImportCallback")
public class JiraIssueImportCallback implements Callback<List<Issue>> {

	private static final Logger LOG = LoggerFactory.getLogger(JiraIssueImportCallback.class);

	@Resource(name = "userService")
	private UserService userService;

	@Resource(name = "issueService")
	private IssueService issueService;

	@Override
	@Async
	@Transactional
	public void execute(List<Issue> issues) {
		for (Issue issue : issues) {
			processIssue(issue);
		}
	}

	private void processIssue(Issue issue) {
		LOG.info("Process issue: " + issue);
		User reporter = null;
		try {
			reporter = userService.getUserByLogin(issue.getReporter().getLogin());
			issue.setReporter(reporter);
		} catch (NoResultException ex) {
			LOG.warn("No existing user");
		}

		issueService.createIssue(issue);
	}
}
