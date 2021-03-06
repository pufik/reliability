package ua.edu.lp.reliability.facade.experiment;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import ua.edu.lp.reliability.facade.converter.Converter;
import ua.edu.lp.reliability.facade.dto.ExperimentDTO;
import ua.edu.lp.reliability.model.annotation.spring.Facade;
import ua.edu.lp.reliability.model.math.StatisticModelExperiment;
import ua.edu.lp.reliability.model.project.Project;
import ua.edu.lp.reliability.service.experiment.ExperimentService;
import ua.edu.lp.reliability.service.project.ProjectService;

@Facade("experimentFacade")
@Transactional
public class DefaultExperimentFacade implements ExperimentFacade {

	private static final Logger LOG = LoggerFactory.getLogger(DefaultExperimentFacade.class);

	@Resource(name = "projectService")
	private ProjectService projectService;

	@Resource(name = "experimentService")
	private ExperimentService experimentService;

	@Resource(name = "experimentConverter")
	private Converter<StatisticModelExperiment, ExperimentDTO> experimentConverter;

	@Override
	public List<ExperimentDTO> getExperimentByProject(Long projectId) {
		LOG.info("Process experiment's data for project ID:" + projectId);

		Project project = projectService.getDetails(projectId);

		Assert.notNull(project, "Project could not be null");

		return experimentConverter.convertAll(project.getExperiments());
	}

	@Override
	public ExperimentDTO getExperimentDetails(Long experimentId) {
		StatisticModelExperiment experiment = experimentService.getExperimentById(experimentId);

		return experimentConverter.convert(experiment);
	}

	@Override
	public ExperimentDTO recalculateExperiment(Long experimentId) {
		StatisticModelExperiment experiment = experimentService.getExperimentById(experimentId);

		Assert.notNull(experiment, "Experiment could not be null");

		experimentService.recalculateExperiment(experiment);
		experimentService.updateExperiment(experiment);

		return experimentConverter.convert(experiment);
	}

}
