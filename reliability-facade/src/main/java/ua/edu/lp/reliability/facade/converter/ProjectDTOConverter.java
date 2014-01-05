package ua.edu.lp.reliability.facade.converter;

import ua.edu.lp.reliability.facade.dto.ProjectDTO;
import ua.edu.lp.reliability.model.annotation.spring.Converter;
import ua.edu.lp.reliability.model.project.Project;

@Converter("projectDTOConverter")
public class ProjectDTOConverter extends AbstractConverter<ProjectDTO, Project> {

	@Override
	public Project convert(ProjectDTO source) {
		return convert(source, new Project());
	}

	@Override
	public Project convert(ProjectDTO source, Project target) {
		target.setName(source.getName());
		target.setDescription(source.getDescription());
		target.setCreateDate(source.getCreateDate());

		return target;
	}

}
