package ua.edu.lp.reliability.service.experiment;

import java.math.BigDecimal;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import ua.edu.lp.reliability.dao.experiment.ExperimentDao;
import ua.edu.lp.reliability.math.models.GoelOkumotoModel;
import ua.edu.lp.reliability.model.math.StatisticModelExperiment;

@Service("experimentService")
public class DefaultExperimentService implements ExperimentService {

	private static final String SEPARATOR = ",";
	@Resource(name = "experimentDao")
	private ExperimentDao experimentDao;

	@Override
	public StatisticModelExperiment getExperimentById(Long experimentId) {
		return experimentDao.getById(experimentId);
	}

	@Override
	public StatisticModelExperiment recalculateExperiment(StatisticModelExperiment experiment) {
		double[] intervals = createIntervals(experiment.getIntervalCount().intValue(), experiment.getIntervalSize().intValue());
		double[] errors = parseErrors(experiment.getErrors());
		double[] initialValues = new double[] { experiment.getInitialL().doubleValue(), experiment.getInitialB().doubleValue() };
		double eps = experiment.getEps().doubleValue();

		GoelOkumotoModel goelOkumotoMethod = new GoelOkumotoModel(intervals, errors, initialValues, eps);

		double[] Yn = goelOkumotoMethod.calculate();

		experiment.setResultL(new BigDecimal(Yn[0]));
		experiment.setResultB(new BigDecimal(Yn[1]));

		return experiment;
	}

	private double[] parseErrors(String errors) {
		String[] errorValues = StringUtils.split(errors, SEPARATOR);

		int arraySize = errorValues.length;
		double[] resultList = new double[arraySize];
		
		for(int i = 0 ; i < arraySize; i++){
			resultList[i] = Double.parseDouble(errorValues[i]);
		}
		
		return resultList;
	}

	private double[] createIntervals(int intervalNum, int intervalSize) {

		double[] intervals = new double[intervalNum];

		for (int i = 0; i < intervalNum; i++) {
			intervals[i] = i + intervalSize;
		}

		return intervals;
	}

	@Override
	public void updateExperiment(StatisticModelExperiment experiment) {
		experimentDao.update(experiment);
	}
}
