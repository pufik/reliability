package ua.edu.lp.reliability.model.issue;

public enum IssueType {
	ISSUE("Issue"), IMPROVEMENT("Improvement"), BUG("Bug"), TEST("Test"), TASK("Task"), SUBTASK("Sub-task"), FEATURE("New Feature"), WISH("Wish");

	private String description;

	private IssueType(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
}
