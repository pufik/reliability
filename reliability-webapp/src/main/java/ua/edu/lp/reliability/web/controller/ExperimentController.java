package ua.edu.lp.reliability.web.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ua.edu.lp.reliability.facade.dto.ExperimentDTO;
import ua.edu.lp.reliability.facade.experiment.ExperimentFacade;

@Controller
@RequestMapping(value = "/experiment")
public class ExperimentController {

	@Resource(name = "experimentFacade")
	private ExperimentFacade experimentFacade;

	@RequestMapping(value = "/project/{projectId}", method = RequestMethod.GET)
	@ResponseBody
	public List<ExperimentDTO> getExperimentsByProject(@PathVariable(value = "projectId") Long projectId) {
		return experimentFacade.getExperimentByProject(projectId);
	}

	@RequestMapping(value = "/{experimentId}", method = RequestMethod.GET)
	@ResponseBody
	public ExperimentDTO getExperimentDetails(@PathVariable(value = "experimentId") Long experimentId) {
		return experimentFacade.getExperimentDetails(experimentId);
	}

	@RequestMapping(value = "/{experimentId}/recalculate", method = RequestMethod.GET)
	@ResponseBody
	public ExperimentDTO recalculateExperiment(@PathVariable(value = "experimentId") Long experimentId) {
		return experimentFacade.recalculateExperiment(experimentId);
	}
}
