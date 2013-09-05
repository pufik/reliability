package ua.edu.lp.reliability.facade.converter;

import ua.edu.lp.reliability.facade.dto.MetricDTO;
import ua.edu.lp.reliability.model.annotation.spring.Converter;
import ua.edu.lp.reliability.model.metric.Metric;

@Converter("metricConverter")
public class MetricConverter extends AbstractConverter<Metric, MetricDTO> {

	@Override
	public MetricDTO convert(Metric source) {

		return convert(source, new MetricDTO());
	}

	@Override
	public MetricDTO convert(Metric source, MetricDTO target) {
		target.setName(source.getName());
		target.setValue(source.getValue());
		target.setFormattedValue(source.getFormattedValue());

		return target;
	}

}
