package ua.edu.lp.reliability.facade.report;

import java.io.OutputStream;
import java.util.List;

import ua.edu.lp.reliability.facade.dto.IssueDTO;
import ua.edu.lp.reliability.facade.util.excel.export.IssueReportItem;
import ua.edu.lp.reliability.model.report.issue.ReportIntervalType;

public interface IssueReportFacade {

	void generateIssueReport(List<IssueDTO> issues, ReportIntervalType intervalType, OutputStream output);

	List<IssueReportItem> createIssueReport(List<IssueDTO> issues, ReportIntervalType intervalType);
}
