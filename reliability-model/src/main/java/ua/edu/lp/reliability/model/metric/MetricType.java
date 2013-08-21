package ua.edu.lp.reliability.model.metric;

public enum MetricType {

	COVERAGE("coverage"), LINES("lines"),
	VIOLATION("violations"), COMMENT_DENSITY("comment_lines_density"),
	COMMENT_LINES("comment_lines"), LINE_OF_CODE("ncloc");

	private String sonarId;

	private MetricType(String sonarId) {
		this.sonarId = sonarId;
	}

	public String getSonarId() {
		return sonarId;
	}
}
