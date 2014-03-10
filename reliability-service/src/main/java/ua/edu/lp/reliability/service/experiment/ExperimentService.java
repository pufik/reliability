package ua.edu.lp.reliability.service.experiment;

import ua.edu.lp.reliability.model.math.StatisticModelExperiment;

public interface ExperimentService {

	StatisticModelExperiment getExperimentById(Long experimentId);

	StatisticModelExperiment recalculateExperiment(StatisticModelExperiment experimant);

	void updateExperiment(StatisticModelExperiment experiment);

}
