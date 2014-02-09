package ua.edu.lp.reliability.model.math;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import ua.edu.lp.reliability.model.project.Project;

@Entity
@Table(name = "reliability_math_experiment")
public class StatisticModelExperiment implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "experiment_id")
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "model_name")
	private String modelName;

	@Column(name = "interval_count")
	private Long intervalCount;

	@Column(name = "interval_size")
	private Long intervalSize;

	@Column(name = "eps")
	private BigDecimal eps;

	@Column(name = "initial_L")
	private BigDecimal initialL;

	@Column(name = "initial_B")
	private BigDecimal initialB;

	@Column(name = "errors")
	private String errors;

	@ManyToOne
	@JoinColumn(name = "project_id")
	private Project project;

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
		result = prime * result + ((eps == null) ? 0 : eps.hashCode());
		result = prime * result + ((errors == null) ? 0 : errors.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((initialB == null) ? 0 : initialB.hashCode());
		result = prime * result + ((initialL == null) ? 0 : initialL.hashCode());
		result = prime * result + ((intervalCount == null) ? 0 : intervalCount.hashCode());
		result = prime * result + ((intervalSize == null) ? 0 : intervalSize.hashCode());
		result = prime * result + ((modelName == null) ? 0 : modelName.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		StatisticModelExperiment other = (StatisticModelExperiment) obj;
		if (eps == null) {
			if (other.eps != null)
				return false;
		} else if (!eps.equals(other.eps))
			return false;
		if (errors == null) {
			if (other.errors != null)
				return false;
		} else if (!errors.equals(other.errors))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (initialB == null) {
			if (other.initialB != null)
				return false;
		} else if (!initialB.equals(other.initialB))
			return false;
		if (initialL == null) {
			if (other.initialL != null)
				return false;
		} else if (!initialL.equals(other.initialL))
			return false;
		if (intervalCount == null) {
			if (other.intervalCount != null)
				return false;
		} else if (!intervalCount.equals(other.intervalCount))
			return false;
		if (intervalSize == null) {
			if (other.intervalSize != null)
				return false;
		} else if (!intervalSize.equals(other.intervalSize))
			return false;
		if (modelName == null) {
			if (other.modelName != null)
				return false;
		} else if (!modelName.equals(other.modelName))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "StatisticModelExperiment [id=" + id + ", name=" + name + ", modelName=" + modelName + ", intervalCount=" + intervalCount + ", intervalSize="
				+ intervalSize + ", eps=" + eps + ", initialL=" + initialL + ", initialB=" + initialB + ", errors=" + errors + ", project=" + project + "]";
	}

}
