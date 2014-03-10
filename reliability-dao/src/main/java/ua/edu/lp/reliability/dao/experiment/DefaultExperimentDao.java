package ua.edu.lp.reliability.dao.experiment;

import java.util.List;

import org.springframework.stereotype.Repository;

import ua.edu.lp.reliability.dao.AbstractBaseDAO;
import ua.edu.lp.reliability.model.math.StatisticModelExperiment;

@Repository("experimentDao")
public class DefaultExperimentDao extends AbstractBaseDAO<StatisticModelExperiment, Long> implements ExperimentDao {

	@Override
	protected Class<StatisticModelExperiment> getEntityClass() {
		return StatisticModelExperiment.class;
	}

	@Override
	public List<StatisticModelExperiment> getAll() {
		throw new UnsupportedOperationException("Not implemented yet.");
	}
}
