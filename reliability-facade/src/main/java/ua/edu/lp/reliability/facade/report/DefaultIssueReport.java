package ua.edu.lp.reliability.facade.report;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ua.edu.lp.reliability.facade.dto.IssueDTO;
import ua.edu.lp.reliability.facade.util.excel.export.IssueReportItem;
import ua.edu.lp.reliability.facade.util.excel.export.IssueReportProvider;
import ua.edu.lp.reliability.model.annotation.spring.Facade;
import ua.edu.lp.reliability.model.report.issue.ReportIntervalType;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

@Facade
public class DefaultIssueReport implements IssueReportFacade {

	private Logger LOG = LoggerFactory.getLogger(DefaultIssueReport.class);

	@Override
	public void generateIssueReport(List<IssueDTO> issues, ReportIntervalType intervalType, OutputStream output) {
		Multimap<Date, IssueDTO> reportItems = HashMultimap.create();
		// TODO: Implement for different report's interval
		for (IssueDTO issue : issues) {
			DateTime dateTime = new DateTime(issue.getCreateDate());
			DateTime yearMounth = new DateTime(dateTime.getYear(), dateTime.monthOfYear().get(), 1, 1, 1, 1);
			reportItems.put(yearMounth.toDate(), issue);
		}

		IssueReportProvider reportProvider = new IssueReportProvider();
		List<IssueReportItem> reportList = new ArrayList<>(reportItems.keySet().size());

		for (Date key : reportItems.keySet()) {
			reportList.add(new IssueReportItem(key, new ArrayList<>(reportItems.asMap().get(key))));
		}

		Collections.sort(reportList);

		Workbook workbook = reportProvider.createReport(reportList);
		try {
			workbook.write(output);
		} catch (IOException e) {
			// TODO: add custom exception
			LOG.error("Cannot write report to the output", e);
		}
	}
}
