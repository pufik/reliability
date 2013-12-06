package ua.edu.lp.reliability.model.user;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import ua.edu.lp.reliability.model.issue.Issue;

@Entity
@Table(name = "user")
@NamedQueries({
	@NamedQuery(name = "User.getByLogin", query = "SELECT u FROM User u WHERE login = :login"),
	@NamedQuery(name = "User.getByLoginAndPassword", query = "SELECT u FROM User u WHERE login = :login AND password =:password")
})
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id")
	private Long uid;

	@Column(name = "login", unique = true)
	private String login;

	@Column(name = "password")
	private String password;

	@Column(name = "full_name")
	private String fullname;

	@Column(name = "role", nullable = false)
	@Enumerated(value = EnumType.STRING)
	private UserRole role;

	@OneToMany(mappedBy = "reporter", fetch = FetchType.LAZY)
	private Set<Issue> reportedIssue;

	@OneToMany(mappedBy = "assignee", fetch = FetchType.LAZY)
	private Set<Issue> assignedIssue;

	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public Set<Issue> getReportedIssue() {
		return reportedIssue;
	}

	public void setReportedIssue(Set<Issue> reportedIssue) {
		this.reportedIssue = reportedIssue;
	}

	public void addReportedIssue(Issue issue) {
		this.reportedIssue.add(issue);
		issue.setReporter(this);
	}

	public Set<Issue> getAssignedIssue() {
		return assignedIssue;
	}

	public void setAssignedIssue(Set<Issue> assignedIssue) {
		this.assignedIssue = assignedIssue;
	}

	public void addAssignedIssue(Issue issue) {
		this.assignedIssue.add(issue);
		issue.setAssignee(this);
	}

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}
}
