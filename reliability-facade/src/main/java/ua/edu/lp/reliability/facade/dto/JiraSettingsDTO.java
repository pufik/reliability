package ua.edu.lp.reliability.facade.dto;


public class JiraSettingsDTO {
	
	private Long id;

	private String url;

	private String userName;

	private String password;

	private String projectUid;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getProjectUid() {
		return projectUid;
	}

	public void setProjectUid(String projectUid) {
		this.projectUid = projectUid;
	}
}
