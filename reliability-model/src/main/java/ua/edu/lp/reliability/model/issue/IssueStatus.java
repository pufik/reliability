package ua.edu.lp.reliability.model.issue;

public enum IssueStatus {
	OPEN("Open"), RESOLVED("Resolved"), CLOSED("Closed"), IN_PROGRESS("In Progress"), PATCH_AVAILABLE("Patch Available");
	
	private String description;

	private IssueStatus(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
}
