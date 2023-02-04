package com.example.webhub.services;

import com.example.webhub.entity.Projects;
import com.example.webhub.mappers.ProjectsMapper;
import com.example.webhub.projects.ProjectsCreateDto;
import com.example.webhub.projects.ProjectsDto;
import com.example.webhub.projects.ProjectsUpdateDto;
import com.example.webhub.repository.ProjectsRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Data
@Slf4j
@RequiredArgsConstructor
public class ProjectsService {

    private final ProjectsMapper projectsMapper;
    private final ProjectsRepository repository;

    public List<ProjectsDto> getByNameLike(String titleLike) {
        return repository.findByTitleLike(titleLike).stream()
                .map(projectsMapper::projectToProjectDto).collect(Collectors.toList());
    }

    public ProjectsDto getById(Long projectId) {
        return projectsMapper.projectToProjectDto(repository.findById(projectId).orElse(null));
    }

    public ProjectsDto update(Long projectId, ProjectsUpdateDto request) {
        Projects project = projectsMapper.projectUpdateRequestToProjectView(request, projectId);
        repository.save(project);
        return projectsMapper.projectToProjectDto(project);
    }

    public void deleteById(Long projectId) {
        repository.deleteById(projectId);
    }

    public ProjectsDto create(ProjectsCreateDto request) {
        log.info("Создаю проект");
        request.setDate(LocalDate.now());
        Projects project = projectsMapper.toProject(request);
        repository.save(project);
        return projectsMapper.projectToProjectDto(project);
    }

    public List<ProjectsDto> getProjects() {
        return StreamSupport.stream(repository.findAll().spliterator(), false)
                .map(projectsMapper::projectToProjectDto).collect(Collectors.toList());
    }

}
