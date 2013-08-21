package ua.edu.lp.reliability.service.project;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.edu.lp.reliability.dao.ProjectDAO;
import ua.edu.lp.reliability.model.project.Project;

@Service
public class DefaultProjectService implements ProjectService {

	@Autowired
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
