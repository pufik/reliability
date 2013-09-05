package ua.edu.lp.reliability.service.metrics;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import ua.edu.lp.reliability.model.metric.Metric;
import ua.edu.lp.reliability.model.metric.MetricType;
import ua.edu.lp.reliability.model.project.Project;
import ua.edu.lp.reliability.sonar.metric.MetricProvider;

@Service("metricService")
public class DefaultMetricsService implements MetricService {

	@Resource(name = "metricProvider")
	private MetricProvider metricProvider;

	private Collection<MetricType> metricTypes;

	@PostConstruct
	private void init() {
		metricTypes = Arrays.asList(new MetricType[] { MetricType.COVERAGE, MetricType.LINES, MetricType.VIOLATION, MetricType.COMMENT_DENSITY,
				MetricType.COMMENT_LINES, MetricType.LINE_OF_CODE });
	}

	@Override
	public List<Metric> getProjectMetrics(Project project) {
		return metricProvider.getMetrics(project.getSettings(), metricTypes);
	}
}
