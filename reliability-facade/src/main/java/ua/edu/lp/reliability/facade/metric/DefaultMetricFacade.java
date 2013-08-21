package ua.edu.lp.reliability.facade.metric;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ua.edu.lp.reliability.facade.converter.Converter;
import ua.edu.lp.reliability.facade.dto.MetricDTO;
import ua.edu.lp.reliability.model.annotation.spring.Facade;
import ua.edu.lp.reliability.model.metric.Metric;
import ua.edu.lp.reliability.model.project.Project;
import ua.edu.lp.reliability.service.metrics.MetricsService;
import ua.edu.lp.reliability.service.project.ProjectService;

@Facade
public class DefaultMetricFacade implements MetricFacade {

	@Autowired
	private ProjectService projectService;

	@Autowired
	private MetricsService metricService;

	@Autowired
	private Converter<Metric, MetricDTO> metricConverter;

	@Override
	public List<MetricDTO> getMetricForProject(Long projectId) {
		Project project = projectService.getDetails(projectId);

		List<Metric> metrics = metricService.getMetricsProject(project);

		return metricConverter.convertAll(metrics);
	}

}
