package ua.edu.lp.reliability.web.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import ua.edu.lp.reliability.facade.dto.MetricDTO;
import ua.edu.lp.reliability.facade.metric.MetricFacade;

@Controller
@RequestMapping(value = "/metrics")
public class MetricController {

	@Resource(name = "metricFacade")
	private MetricFacade metricFacade;

	@RequestMapping(value = "/project/{projectId}")
	public @ResponseBody
	List<MetricDTO> getProjectMetrics(@PathVariable(value = "projectId") Long projectId) {
		List<MetricDTO> metrics = metricFacade.getMetricForProject(projectId);

		return metrics;
	}
}
