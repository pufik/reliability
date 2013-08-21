package ua.edu.lp.reliability.facade.converter;

import ua.edu.lp.reliability.facade.dto.IssueDTO;
import ua.edu.lp.reliability.model.annotation.spring.Converter;
import ua.edu.lp.reliability.model.issue.Issue;

@Converter
public class IssueConverter extends AbstractConverter<Issue, IssueDTO> {

	@Override
	public IssueDTO convert(Issue source) {
		return convert(source, new IssueDTO());
	}

	@Override
	public IssueDTO convert(Issue source, IssueDTO target) {
		target.setId(source.getId());
		target.setKey(source.getKey());
		target.setSummary(source.getSummary());
		target.setCreateDate(source.getCreateDate());
		target.setUpdateDate(source.getUpdateDate());
		target.setType(source.getType());
		target.setPriority(source.getPriority());
		target.setResolution(source.getResolution());
		target.setStatus(source.getStatus());

		return target;
	}

}
