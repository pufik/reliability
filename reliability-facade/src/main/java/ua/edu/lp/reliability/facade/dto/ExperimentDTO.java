package ua.edu.lp.reliability.facade.dto;

import java.math.BigDecimal;

public class ExperimentDTO {

	private Long id;

	private String name;

	private String modelName;

	private Long intervalCount;

	private Long intervalSize;

	private BigDecimal eps;

	private BigDecimal initialL;

	private BigDecimal initialB;

	private String errors;

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

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public Long getIntervalCount() {
		return intervalCount;
	}

	public void setIntervalCount(Long intervalCount) {
		this.intervalCount = intervalCount;
	}

	public Long getIntervalSize() {
		return intervalSize;
	}

	public void setIntervalSize(Long intervalSize) {
		this.intervalSize = intervalSize;
	}

	public BigDecimal getEps() {
		return eps;
	}

	public void setEps(BigDecimal eps) {
		this.eps = eps;
	}

	public BigDecimal getInitialL() {
		return initialL;
	}

	public void setInitialL(BigDecimal initialL) {
		this.initialL = initialL;
	}

	public BigDecimal getInitialB() {
		return initialB;
	}

	public void setInitialB(BigDecimal initialB) {
		this.initialB = initialB;
	}

	public String getErrors() {
		return errors;
	}

	public void setErrors(String errors) {
		this.errors = errors;
	}
}
