package ua.edu.lp.reliability.dao.impl;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Repository;

import ua.edu.lp.reliability.dao.ProjectDAO;
import ua.edu.lp.reliability.model.project.Project;

@Repository
public class ProjectDAOImpl extends AbstractBaseDAO<Project, Long> implements ProjectDAO {

	private static final String PROJECT_GET_ALL = "Project.getAll";

	@Override
	protected Class<Project> getEntityClass() {
		return Project.class;
	}

	@Override
	public List<Project> getAll() {
		return executeNamedQuery(PROJECT_GET_ALL, Collections.<String, Object> emptyMap());
	}
}
