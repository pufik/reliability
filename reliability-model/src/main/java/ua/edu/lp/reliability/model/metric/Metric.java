package ua.edu.lp.reliability.model.metric;

public class Metric {

	private String name;

	private Double value;
	
	private String formattedValue;

	private MetricType type;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}
	
	public String getFormattedValue() {
		return formattedValue;
	}

	public void setFormattedValue(String formattedValue) {
		this.formattedValue = formattedValue;
	}

	public MetricType getType() {
		return type;
	}

	public void setType(MetricType type) {
		this.type = type;
	}
}
