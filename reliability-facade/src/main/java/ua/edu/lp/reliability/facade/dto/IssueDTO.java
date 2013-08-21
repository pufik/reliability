package ua.edu.lp.reliability.facade.dto;

import java.util.Date;

import ua.edu.lp.reliability.model.issue.IssuePriority;
import ua.edu.lp.reliability.model.issue.IssueResolution;
import ua.edu.lp.reliability.model.issue.IssueStatus;
import ua.edu.lp.reliability.model.issue.IssueType;

public class IssueDTO {
	private Long id;

	private String key;

	private IssueType type;

	private IssueStatus status;

	private IssuePriority priority;

	private IssueResolution resolution;

	private UserDTO reporter;

	private UserDTO assignee;

	private Date createDate;

	private Date updateDate;

	private String summary;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public IssueType getType() {
		return type;
	}

	public void setType(IssueType type) {
		this.type = type;
	}

	public IssueStatus getStatus() {
		return status;
	}

	public void setStatus(IssueStatus status) {
		this.status = status;
	}

	public IssuePriority getPriority() {
		return priority;
	}

	public void setPriority(IssuePriority priority) {
		this.priority = priority;
	}

	public IssueResolution getResolution() {
		return resolution;
	}

	public void setResolution(IssueResolution resolution) {
		this.resolution = resolution;
	}

	public UserDTO getReporter() {
		return reporter;
	}

	public void setReporter(UserDTO reporter) {
		this.reporter = reporter;
	}

	public UserDTO getAssignee() {
		return assignee;
	}

	public void setAssignee(UserDTO assignee) {
		this.assignee = assignee;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}
}
