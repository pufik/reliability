package ua.edu.lp.reliability.model.issue;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import ua.edu.lp.reliability.model.project.Project;
import ua.edu.lp.reliability.model.user.User;

@Entity
@Table(name = "issue")
@NamedQueries({ @NamedQuery(name = "Issue.getAll", query = "SELECT i FROM Issue i") })
public class Issue implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "issue_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "issue_key", unique = true)
	private String key;

	@Column(name = "type")
	@Enumerated(value = EnumType.STRING)
	private IssueType type;

	@Column(name = "status")
	@Enumerated(value = EnumType.STRING)
	private IssueStatus status;

	@Column(name = "priority")
	@Enumerated(value = EnumType.STRING)
	private IssuePriority priority;

	@Column(name = "resolution")
	@Enumerated(value = EnumType.STRING)
	private IssueResolution resolution;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "reporter_id")
	private User reporter;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "assignee_id")
	private User assignee;

	@Column(name = "create_date")
	private Date createDate;

	@Column(name = "update_date")
	private Date updateDate;

	@Column(name = "summary")
	private String summary;

	@ManyToOne
	@JoinColumn(name = "project_id")
	private Project project;

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

	public User getReporter() {
		return reporter;
	}

	public void setReporter(User reporter) {
		this.reporter = reporter;
	}

	public User getAssignee() {
		return assignee;
	}

	public void setAssignee(User assignee) {
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

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((key == null) ? 0 : key.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Issue other = (Issue) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (key == null) {
			if (other.key != null)
				return false;
		} else if (!key.equals(other.key))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Issue [id=" + id + ", key=" + key + ", type=" + type + ", status=" + status + ", priority=" + priority + ", resolution=" + resolution
				+ ", reporter=" + reporter + ", assignee=" + assignee + ", createDate=" + createDate + ", updateDate=" + updateDate //+ ", summary=" + summary
				+ ", project=" + project + "]";
	}
}
