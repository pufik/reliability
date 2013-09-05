package ua.edu.lp.reliability.service.project;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import ua.edu.lp.reliability.dao.project.ProjectDAO;
import ua.edu.lp.reliability.model.project.Project;

@Service("projectService")
public class DefaultProjectService implements ProjectService {

	@Resource(name = "projectDao")
	private ProjectDAO projectDao;

	@Override
	public List<Project> getAll() {
		return projectDao.getAll();
	}

	@Override
	public Project getDetails(Long projectId) {
		return projectDao.getById(projectId);
	}

	@Override
	public void save(Project project) {
		projectDao.update(project);
	}

}
