package ua.edu.lp.reliability.service.metrics;

import java.util.List;

import ua.edu.lp.reliability.model.metric.Metric;
import ua.edu.lp.reliability.model.project.Project;

public interface MetricService {

	List<Metric> getProjectMetrics(Project project);
}