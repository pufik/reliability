package ua.edu.lp.reliability.facade.converter;

import ua.edu.lp.reliability.facade.dto.SonarSettingsDTO;
import ua.edu.lp.reliability.model.annotation.spring.Converter;
import ua.edu.lp.reliability.model.metric.SonarSettings;

@Converter("sonarSettingsConverter")
public class SonarSettingsConverter extends AbstractConverter<SonarSettings, SonarSettingsDTO> {

	@Override
	public SonarSettingsDTO convert(SonarSettings source) {
		return convert(source, new SonarSettingsDTO());
	}

	@Override
	public SonarSettingsDTO convert(SonarSettings source, SonarSettingsDTO target) {
		target.setId(source.getId());
		target.setUserName(source.getUserName());
		target.setPassword(source.getPassword());
		target.setProjectUid(source.getProjectUID());
		target.setUrl(source.getUrl());

		return target;
	}
}
