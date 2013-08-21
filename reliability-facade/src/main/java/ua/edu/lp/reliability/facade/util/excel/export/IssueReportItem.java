package ua.edu.lp.reliability.facade.util.excel.export;

import java.util.Date;
import java.util.List;

import ua.edu.lp.reliability.facade.dto.IssueDTO;

public class IssueReportItem implements Comparable<IssueReportItem> {

	private Date startDate;

	private List<IssueDTO> issues;

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
