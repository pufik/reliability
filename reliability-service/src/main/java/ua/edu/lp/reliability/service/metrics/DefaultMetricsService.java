package ua.edu.lp.reliability.service.metrics;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.edu.lp.reliability.model.metric.Metric;
import ua.edu.lp.reliability.model.metric.MetricType;
import ua.edu.lp.reliability.model.project.Project;
import ua.edu.lp.reliability.sonar.metric.MetricProvider;

@Service
public class DefaultMetricsService implements MetricsService {

	@Autowired
	private MetricProvider metricProvider;

	private Collection<MetricType> metricTypes;

	@PostConstruct
	private void init() {
		metricTypes = Arrays.asList(new MetricType[] { MetricType.COVERAGE, MetricType.LINES, MetricType.VIOLATION, MetricType.COMMENT_DENSITY,
				MetricType.COMMENT_LINES, MetricType.LINE_OF_CODE });
	}

	@Override
	public List<Metric> getMetricsProject(Project project) {
		return metricProvider.getMetrics(project.getSettings(), metricTypes);
	}
}
