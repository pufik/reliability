package ua.edu.lp.reliability.sonar.metric;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.sonar.wsclient.Host;
import org.sonar.wsclient.Sonar;
import org.sonar.wsclient.connectors.HttpClient3Connector;
import org.sonar.wsclient.services.Measure;
import org.sonar.wsclient.services.Resource;
import org.sonar.wsclient.services.ResourceQuery;
import org.sonar.wsclient.services.TimeMachine;
import org.sonar.wsclient.services.TimeMachineQuery;
import org.springframework.stereotype.Repository;

import ua.edu.lp.reliability.model.metric.Metric;
import ua.edu.lp.reliability.model.metric.MetricType;
import ua.edu.lp.reliability.model.metric.SonarSettings;

@Repository("metricProvider")
public class SonarMetricProvider implements MetricProvider {

	@Override
	public List<Metric> getMetrics(SonarSettings settings, Collection<MetricType> types) {
		List<Metric> metrics = new LinkedList<>();
		Sonar sonar = new Sonar(new HttpClient3Connector(new Host(settings.getUrl(), settings.getUserName(), settings.getPassword())));

		Resource project = sonar.find(ResourceQuery.createForMetrics(settings.getProjectUID(), convertMetricIds(types)));

		for (MetricType type : types) {
			Measure measure = project.getMeasure(type.getSonarId());
			metrics.add(convertMeasure(measure));
		}

		return metrics;
	}

	private Metric convertMeasure(Measure measure) {
		Metric metric = new Metric();
		metric.setName(measure.getMetricKey());
		metric.setValue(measure.getValue());
		metric.setFormattedValue(measure.getFormattedValue());

		return metric;
	}

	private String[] convertMetricIds(Collection<MetricType> types) {
		String[] metrics = new String[types.size()];

		int i = 0;
		for (MetricType type : types) {
			metrics[i] = type.getSonarId();
			i++;
		}

		return metrics;
	}

	private void getTimeMachine(SonarSettings settings, Collection<MetricType> types) {
		List<Metric> metrics = new LinkedList<>();
		Sonar sonar = new Sonar(new HttpClient3Connector(new Host(settings.getUrl(), settings.getUserName(), settings.getPassword())));

		TimeMachine results = sonar.find(TimeMachineQuery.createForMetrics(settings.getProjectUID(), convertMetricIds(types)));
	}
}
