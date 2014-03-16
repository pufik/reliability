package ua.edu.lp.reliability.facade.util.excel.export;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import ua.edu.lp.reliability.facade.dto.IssueDTO;
import ua.edu.lp.reliability.model.issue.IssueType;

public class IssueReportItem implements Comparable<IssueReportItem> {

	private Date startDate;

	private List<IssueDTO> issues;

	private Map<IssueType, Collection<IssueDTO>> issueByType;

	private Map<IssueType, BigDecimal> issuePerReporter;

	public IssueReportItem() {
		super();
	}

	public IssueReportItem(Date startDate, List<IssueDTO> issues) {
		this.startDate = startDate;
		this.issues = issues;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public List<IssueDTO> getIssues() {
		return issues;
	}

	public void setIssues(List<IssueDTO> issues) {
		this.issues = issues;
	}

	public Map<IssueType, Collection<IssueDTO>> getIssueByType() {
		return issueByType;
	}

	public void setIssueByType(Map<IssueType, Collection<IssueDTO>> issueByType) {
		this.issueByType = issueByType;
	}

	public Map<IssueType, BigDecimal> getIssuePerReporter() {
		return issuePerReporter;
	}

	public void setIssuePerReporter(Map<IssueType, BigDecimal> issuePerReporter) {
		this.issuePerReporter = issuePerReporter;
	}

	@Override
	public int compareTo(IssueReportItem item) {
		int result = 1;

		if (this.startDate.equals(item.getStartDate())) {
			result = 0;
		} else if (this.startDate.before(item.getStartDate())) {
			result = -1;
		}

		return result;
	}
}
