package ua.edu.lp.reliability.sonar.metric;

import java.util.Collection;
import java.util.List;

import ua.edu.lp.reliability.model.metric.Metric;
import ua.edu.lp.reliability.model.metric.MetricType;
import ua.edu.lp.reliability.model.metric.SonarSettings;

public interface MetricProvider {
	
	List<Metric> getMetrics(SonarSettings settings, Collection<MetricType> types);
	
}
