package ua.edu.lp.reliability.service.metrics;

import java.util.List;

import ua.edu.lp.reliability.model.metric.Metric;
import ua.edu.lp.reliability.model.project.Project;

public interface MetricsService {

	List<Metric> getMetricsProject(Project project);

}