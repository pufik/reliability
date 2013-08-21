package ua.edu.lp.reliability.facade.metric;

import java.util.List;

import ua.edu.lp.reliability.facade.dto.MetricDTO;

public interface MetricFacade {
	public List<MetricDTO> getMetricForProject(Long projectId);
}
