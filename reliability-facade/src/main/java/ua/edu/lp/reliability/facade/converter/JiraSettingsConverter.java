package ua.edu.lp.reliability.facade.converter;

import ua.edu.lp.reliability.facade.dto.JiraSettingsDTO;
import ua.edu.lp.reliability.model.annotation.spring.Converter;
import ua.edu.lp.reliability.model.jira.JiraSettings;

@Converter("jiraSettingsConverter")
public class JiraSettingsConverter extends AbstractConverter<JiraSettings, JiraSettingsDTO> {

	@Override
	public JiraSettingsDTO convert(JiraSettings source) {
		return convert(source, new JiraSettingsDTO());
	}

	@Override
	public JiraSettingsDTO convert(JiraSettings source, JiraSettingsDTO target) {
		target.setId(source.getId());
		target.setUserName(source.getUserName());
		target.setPassword(source.getPassword());
		target.setUrl(source.getUrl());
		target.setProjectUid(source.getProjectUid());
		
		return target;
	}
}
