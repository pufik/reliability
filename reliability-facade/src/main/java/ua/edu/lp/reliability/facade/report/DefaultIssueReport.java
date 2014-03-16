package ua.edu.lp.reliability.facade.report;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.ss.usermodel.Workbook;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ua.edu.lp.reliability.facade.dto.IssueDTO;
import ua.edu.lp.reliability.facade.dto.UserDTO;
import ua.edu.lp.reliability.facade.util.excel.export.IssueReportItem;
import ua.edu.lp.reliability.facade.util.excel.export.IssueReportProvider;
import ua.edu.lp.reliability.model.annotation.spring.Facade;
import ua.edu.lp.reliability.model.issue.IssueType;
import ua.edu.lp.reliability.model.report.issue.ReportIntervalType;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

@Facade("issueReportFacade")
public class DefaultIssueReport implements IssueReportFacade {

	private static final Logger LOG = LoggerFactory.getLogger(DefaultIssueReport.class);

	@Override
	public void generateIssueReport(List<IssueDTO> issues, ReportIntervalType intervalType, OutputStream output) {
		IssueReportProvider reportProvider = new IssueReportProvider();
		List<IssueReportItem> reportList = createIssueReport(issues, intervalType);

		Workbook workbook = reportProvider.createReport(reportList);
		try {
			workbook.write(output);
		} catch (IOException e) {
			// TODO: add custom exception
			LOG.error("Cannot write report to the output", e);
		}
	}

	@Override
	public List<IssueReportItem> createIssueReport(List<IssueDTO> issues, ReportIntervalType intervalType) {
		Multimap<Date, IssueDTO> reportItems = HashMultimap.create();

		// TODO: Implement for different report's interval
		for (IssueDTO issue : issues) {
			DateTime dateTime = new DateTime(issue.getCreateDate());
			DateTime yearMounth = new DateTime(dateTime.getYear(), dateTime.monthOfYear().get(), 1, 1, 1, 1);
			reportItems.put(yearMounth.toDate(), issue);
		}

		List<IssueReportItem> reportList = new ArrayList<>(reportItems.keySet().size());

		for (Date key : reportItems.keySet()) {
			reportList.add(createReportItem(key, reportItems.asMap().get(key)));
		}

		Collections.sort(reportList);

		return reportList;
	}

	private IssueReportItem createReportItem(Date key, Collection<IssueDTO> issues) {
		IssueReportItem reportItem = new IssueReportItem(key, new ArrayList<>(issues));
		Map<IssueType, BigDecimal> issuePerReporter = new HashMap<>();

		Map<IssueType, Collection<IssueDTO>> issueByType = groupByIssueType(issues);
		Set<UserDTO> reporters = getReporters(issues);

		for (IssueType type : issueByType.keySet()) {
			BigDecimal issueTypePerReporter = new BigDecimal(issueByType.get(type).size() / reporters.size());
			issuePerReporter.put(type, issueTypePerReporter);
		}

		reportItem.setIssueByType(issueByType);
		reportItem.setIssuePerReporter(issuePerReporter);

		return reportItem;
	}

	private Map<IssueType, Collection<IssueDTO>> groupByIssueType(Collection<IssueDTO> issues) {
		Multimap<IssueType, IssueDTO> issueByType = HashMultimap.create();
		issueByType.putAll(IssueType.ISSUE, issues);

		for (IssueDTO issue : issues) {
			issueByType.put(issue.getType(), issue);
		}

		return issueByType.asMap();
	}

	private Set<UserDTO> getReporters(Collection<IssueDTO> issues) {
		Set<UserDTO> reporters = new HashSet<>();

		for (IssueDTO issue : issues) {
			reporters.add(issue.getReporter());
		}

		return reporters;
	}
}
