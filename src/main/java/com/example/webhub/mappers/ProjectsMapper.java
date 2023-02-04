package com.example.webhub.mappers;

import com.example.webhub.entity.Projects;
import com.example.webhub.projects.ProjectsCreateDto;
import com.example.webhub.projects.ProjectsDto;
import com.example.webhub.projects.ProjectsUpdateDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProjectsMapper {

    ProjectsDto projectToProjectDto(Projects entity);

    Projects projectUpdateRequestToProjectView(ProjectsUpdateDto dto, Long id);

    Projects toProject(ProjectsCreateDto dto);

}
