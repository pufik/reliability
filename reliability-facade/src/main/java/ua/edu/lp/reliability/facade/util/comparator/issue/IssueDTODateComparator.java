package ua.edu.lp.reliability.facade.util.comparator.issue;

import java.util.Comparator;
import java.util.Date;

import ua.edu.lp.reliability.facade.dto.IssueDTO;

public class IssueDTODateComparator implements Comparator<IssueDTO> {

	@Override
	public int compare(IssueDTO issue1, IssueDTO issue2) {
		int result = 1;

		Date issue1CreateDate = issue1.getCreateDate();
		Date issue2CreateDate = issue2.getCreateDate();

		if (issue1CreateDate.equals(issue2CreateDate)) {
			result = 0;
		} else if (issue1CreateDate.before(issue2CreateDate)) {
			result = -1;
		}

		return result;
	}
}
