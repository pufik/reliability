package ua.edu.lp.reliability.facade.dto;

import java.util.Date;

public class ProjectDTO {

	private Long id;

	private String name;

	private String description;

	private Date createDate;

	private JiraSettingsDTO jiraSettings;

	private SonarSettingsDTO sonarSettings;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public JiraSettingsDTO getJiraSettings() {
		return jiraSettings;
	}

	public void setJiraSettings(JiraSettingsDTO jiraSettings) {
		this.jiraSettings = jiraSettings;
	}

	public SonarSettingsDTO getSonarSettings() {
		return sonarSettings;
	}

	public void setSonarSettings(SonarSettingsDTO sonarSettings) {
		this.sonarSettings = sonarSettings;
	}
}
