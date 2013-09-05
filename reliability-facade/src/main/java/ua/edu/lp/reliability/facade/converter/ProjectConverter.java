package ua.edu.lp.reliability.facade.converter;

import ua.edu.lp.reliability.facade.dto.ProjectDTO;
import ua.edu.lp.reliability.model.annotation.spring.Converter;
import ua.edu.lp.reliability.model.project.Project;

@Converter("projectConverter")
public class ProjectConverter extends AbstractConverter<Project, ProjectDTO> {

	@Override
	public ProjectDTO convert(Project source) {
		return convert(source, new ProjectDTO());
	}

	@Override
	public ProjectDTO convert(Project source, ProjectDTO target) {
		target.setId(source.getId());
		target.setName(source.getName());
		target.setDescription(source.getDescription());
		target.setCreateDate(source.getCreateDate());

		return target;
	}

}
