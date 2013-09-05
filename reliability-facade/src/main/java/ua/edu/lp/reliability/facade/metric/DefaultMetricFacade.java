package ua.edu.lp.reliability.facade.metric;

import java.util.List;

import javax.annotation.Resource;

import ua.edu.lp.reliability.facade.converter.Converter;
import ua.edu.lp.reliability.facade.dto.MetricDTO;
import ua.edu.lp.reliability.model.annotation.spring.Facade;
import ua.edu.lp.reliability.model.metric.Metric;
import ua.edu.lp.reliability.model.project.Project;
import ua.edu.lp.reliability.service.metrics.MetricService;
import ua.edu.lp.reliability.service.project.ProjectService;

@Facade("metricFacade")
public class DefaultMetricFacade implements MetricFacade {

	@Resource(name = "projectService")
	private ProjectService projectService;

	@Resource(name = "metricService")
	private MetricService metricService;

	@Resource(name = "metricConverter")
	private Converter<Metric, MetricDTO> metricConverter;

	@Override
	public List<MetricDTO> getMetricForProject(Long projectId) {
		Project project = projectService.getDetails(projectId);

		List<Metric> metrics = metricService.getProjectMetrics(project);

		return metricConverter.convertAll(metrics);
	}

}
