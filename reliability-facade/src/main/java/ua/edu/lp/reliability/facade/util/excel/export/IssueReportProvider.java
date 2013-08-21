package ua.edu.lp.reliability.facade.util.excel.export;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import ua.edu.lp.reliability.facade.dto.IssueDTO;
import ua.edu.lp.reliability.facade.dto.UserDTO;
import ua.edu.lp.reliability.model.issue.IssueType;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

public class IssueReportProvider {

	private static final String DATE_PATTERN = "dd/MM/yyyy";
	private static final String ISSUE_SHEET_NAME = "Issues by interval";

	public Workbook createReport(List<IssueReportItem> reportItems) {
		Workbook wb = new HSSFWorkbook();
		Sheet issueSheet = wb.createSheet(ISSUE_SHEET_NAME);
		Row headerRow = issueSheet.createRow(0);
		populateGeneralCell(wb, headerRow.createCell(0), "Int. #");
		populateGeneralCell(wb, headerRow.createCell(1), "Date");
		populateGeneralCell(wb, headerRow.createCell(2), "Issue");
		populateGeneralCell(wb, headerRow.createCell(3), "Reporter");
		populateGeneralCell(wb, headerRow.createCell(4), "Issue per reporter");
		populateGeneralCell(wb, headerRow.createCell(5), IssueType.BUG.getDescription());
		populateGeneralCell(wb, headerRow.createCell(6), IssueType.BUG.getDescription() + " per reporter");
		populateGeneralCell(wb, headerRow.createCell(7), IssueType.TASK.getDescription());
		populateGeneralCell(wb, headerRow.createCell(8), IssueType.SUBTASK.getDescription());
		populateGeneralCell(wb, headerRow.createCell(9), IssueType.TEST.getDescription());
		populateGeneralCell(wb, headerRow.createCell(10), IssueType.FEATURE.getDescription());
		populateGeneralCell(wb, headerRow.createCell(11), IssueType.IMPROVMENT.getDescription());
		populateGeneralCell(wb, headerRow.createCell(12), IssueType.WISH.getDescription());

		int i = 1;
		for (IssueReportItem item : reportItems) {
			Row issueRow = issueSheet.createRow(i);
			populateGeneralCell(wb, issueRow.createCell(0), "" + i);
			SimpleDateFormat formater = new SimpleDateFormat(DATE_PATTERN);
			populateGeneralCell(wb, issueRow.createCell(1), formater.format(item.getStartDate()));
			populateGeneralCell(wb, issueRow.createCell(2), "" + item.getIssues().size());

			Multimap<IssueType, IssueDTO> issueByTypes = HashMultimap.create();
			Set<UserDTO> reporters = new HashSet<>();

			for (IssueDTO issue : item.getIssues()) {
				issueByTypes.put(issue.getType(), issue);
				reporters.add(issue.getReporter());
			}
			
			double issuePerReporter = ((double) item.getIssues().size()) / reporters.size();
			
			DecimalFormat format = new DecimalFormat();
			populateGeneralCell(wb, issueRow.createCell(3), "" + reporters.size());
			populateGeneralCell(wb, issueRow.createCell(4), format.format(issuePerReporter));

			populateIssueTypes(wb, issueRow.createCell(5), issueByTypes.asMap().get(IssueType.BUG));
			populateIssuePerUser(wb, issueRow.createCell(6), issueByTypes.asMap().get(IssueType.BUG));
			
			populateIssueTypes(wb, issueRow.createCell(7), issueByTypes.asMap().get(IssueType.TASK));
			populateIssueTypes(wb, issueRow.createCell(8), issueByTypes.asMap().get(IssueType.SUBTASK));
			populateIssueTypes(wb, issueRow.createCell(9), issueByTypes.asMap().get(IssueType.TEST));
			populateIssueTypes(wb, issueRow.createCell(10), issueByTypes.asMap().get(IssueType.FEATURE));
			populateIssueTypes(wb, issueRow.createCell(11), issueByTypes.asMap().get(IssueType.IMPROVMENT));
			populateIssueTypes(wb, issueRow.createCell(12), issueByTypes.asMap().get(IssueType.WISH));

			i++;
		}

		return wb;
	}

	private void populateIssuePerUser(Workbook wb, Cell cell, Collection<IssueDTO> issues) {
		if (issues != null) {
			Set<UserDTO> reporters = new HashSet<>();

			for (IssueDTO issue : issues) {
				reporters.add(issue.getReporter());
			}
			
			double issuePerReporter = (double) issues.size() / reporters.size();
			DecimalFormat format = new DecimalFormat();
			populateGeneralCell(wb, cell, format.format(issuePerReporter));
		} else {
			populateGeneralCell(wb, cell, "-");
		}
	}

	private void populateIssueTypes(Workbook wb, Cell cell, Collection<IssueDTO> issues) {
		if (issues != null) {
			populateGeneralCell(wb, cell, "" + issues.size());
		} else {
			populateGeneralCell(wb, cell, "-");
		}
	}

	private void populateGeneralCell(Workbook wb, Cell cell, String value) {
		cell.setCellValue(value);
		CellStyle cellStyle = wb.createCellStyle();
		cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
		cell.setCellStyle(cellStyle);
	}
}