package ua.edu.lp.reliability.facade.experiment;

import java.util.List;

import ua.edu.lp.reliability.facade.dto.ExperimentDTO;

public interface ExperimentFacade {

	List<ExperimentDTO> getExperimentByProject(Long projectId);

}
