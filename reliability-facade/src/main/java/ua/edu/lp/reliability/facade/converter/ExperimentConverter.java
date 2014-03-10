package ua.edu.lp.reliability.facade.converter;

import ua.edu.lp.reliability.facade.dto.ExperimentDTO;
import ua.edu.lp.reliability.model.annotation.spring.Converter;
import ua.edu.lp.reliability.model.math.StatisticModelExperiment;

@Converter("experimentConverter")
public class ExperimentConverter extends AbstractConverter<StatisticModelExperiment, ExperimentDTO> {

	@Override
	public ExperimentDTO convert(StatisticModelExperiment source) {
		return convert(source, new ExperimentDTO());
	}

	@Override
	public ExperimentDTO convert(StatisticModelExperiment source, ExperimentDTO target) {
		target.setId(source.getId());
		target.setName(source.getName());
		target.setModelName(source.getModelName());
		target.setIntervalCount(source.getIntervalCount());
		target.setIntervalSize(source.getIntervalSize());
		target.setInitialL(source.getInitialL());
		target.setInitialB(source.getInitialB());
		target.setResultL(source.getResultL());
		target.setResultB(source.getResultB());
		target.setEps(source.getEps());
		target.setErrors(source.getErrors());

		return target;
	}

}
