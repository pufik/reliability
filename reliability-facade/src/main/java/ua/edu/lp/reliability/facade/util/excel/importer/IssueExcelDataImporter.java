package ua.edu.lp.reliability.facade.util.excel.importer;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ua.edu.lp.reliability.model.issue.Issue;
import ua.edu.lp.reliability.model.issue.IssuePriority;
import ua.edu.lp.reliability.model.issue.IssueStatus;
import ua.edu.lp.reliability.model.issue.IssueType;
import ua.edu.lp.reliability.model.user.User;

public class IssueExcelDataImporter {
	private static final String PARSE_DATE_FORMAT = "dd/MMM/y HH:mm";

	private static final Logger LOGGER = LoggerFactory.getLogger(IssueExcelDataImporter.class);

	private final Map<String, IssueType> issueTypeMapping;

	private final Map<String, IssueStatus> issueStatusMapping;

	private final Map<String, User> usersCache;

	public IssueExcelDataImporter() {
		issueTypeMapping = new HashMap<>();
		issueTypeMapping.put(IssueType.TASK.getDescription(), IssueType.TASK);
		issueTypeMapping.put(IssueType.SUBTASK.getDescription(), IssueType.SUBTASK);
		issueTypeMapping.put(IssueType.IMPROVEMENT.getDescription(), IssueType.IMPROVEMENT);
		issueTypeMapping.put(IssueType.BUG.getDescription(), IssueType.BUG);
		issueTypeMapping.put(IssueType.FEATURE.getDescription(), IssueType.FEATURE);
		issueTypeMapping.put(IssueType.TEST.getDescription(), IssueType.TEST);
		issueTypeMapping.put(IssueType.WISH.getDescription(), IssueType.WISH);

		issueStatusMapping = new HashMap<>();
		issueStatusMapping.put(IssueStatus.OPEN.getDescription(), IssueStatus.OPEN);
		issueStatusMapping.put(IssueStatus.CLOSED.getDescription(), IssueStatus.CLOSED);
		issueStatusMapping.put(IssueStatus.IN_PROGRESS.getDescription(), IssueStatus.IN_PROGRESS);
		issueStatusMapping.put(IssueStatus.RESOLVED.getDescription(), IssueStatus.RESOLVED);
		issueStatusMapping.put(IssueStatus.PATCH_AVAILABLE.getDescription(), IssueStatus.PATCH_AVAILABLE);

		usersCache = new HashMap<>();

	}

	public List<Issue> parseIssue(InputStream inputStream, int sheetNum, int startRow) {
		List<Issue> issues = new LinkedList<>();

		try {
			HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
			HSSFSheet sheet = workbook.getSheetAt(sheetNum);

			int i = 0;
			startRow--;
			for (Row row : sheet) {
				if (i >= startRow) {
					issues.add(parseIssueRow(row));
				}
				i++;
			}
		} catch (FileNotFoundException e) {
			// TODO: Add custom exception
			LOGGER.error("File not found", e);
		} catch (IOException e) {
			// TODO: Add custom exception
			LOGGER.error("Exception while read file", e);
		} catch (ParseException e) {
			// TODO: Add custom exception
			LOGGER.error("Exception while parsing data", e);
		}

		return issues;
	}

	private Issue parseIssueRow(Row row) throws ParseException {
		Issue issue = new Issue();
		// TODO: Refactor numbers of cells

		issue.setType(issueTypeMapping.get(row.getCell(0).getStringCellValue()));
		issue.setKey(row.getCell(1).getStringCellValue());
		issue.setSummary(row.getCell(2).getStringCellValue());

		String reporter = row.getCell(4).getStringCellValue();
		issue.setReporter(getUser(reporter));

		// TODO: Add resolution
		// TODO: Add assignee

		issue.setPriority(IssuePriority.valueOf(row.getCell(5).getStringCellValue().toUpperCase()));
		issue.setStatus(issueStatusMapping.get(row.getCell(6).getStringCellValue()));

		DateFormat formatter = new SimpleDateFormat(PARSE_DATE_FORMAT, Locale.ENGLISH);
		issue.setCreateDate(formatter.parse(row.getCell(8).getStringCellValue()));

		formatter = new SimpleDateFormat(PARSE_DATE_FORMAT, Locale.ENGLISH);
		issue.setUpdateDate(formatter.parse(row.getCell(9).getStringCellValue()));

		return issue;
	}

	private User getUser(String userName) {
		User user = usersCache.get(userName);

		if (user == null) {
			user = new User();
			user.setFullname(userName);
			usersCache.put(userName, user);
		}

		return user;
	}
}
