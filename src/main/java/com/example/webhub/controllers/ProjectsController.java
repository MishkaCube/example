package com.example.webhub.controllers;

import com.example.webhub.projects.ProjectsCreateDto;
import com.example.webhub.projects.ProjectsDto;
import com.example.webhub.projects.ProjectsUpdateDto;
import com.example.webhub.services.FileService;
import com.example.webhub.services.ProjectsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.PositiveOrZero;
import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin("https://admin.devcloudy.ru")
@Tag(name = "Projects Controller", description = "API контролера по работе с нашими проектами")
public class ProjectsController {

    private final ProjectsService projectsService;

    private final FileService fileService;

    public ProjectsController(ProjectsService projectsService, FileService fileService) {
        this.projectsService = projectsService;
        this.fileService = fileService;
    }

    @Value("${upload.path}")
    private String uploadPath;

    @Operation(description = "Получение списка проектов")
    @GetMapping(
            value = "api/getProjects",
            produces = {"application/json"}
    )
    public ResponseEntity<List<ProjectsDto>> getProjects() {
        return ResponseEntity.ok(projectsService.getProjects());
    }

    @Operation(description = "Получение проекта")
    @GetMapping(
            value = "api/projects/{projectId}",
            produces = {"application/json"}
    )
    public ResponseEntity<ProjectsDto> getProject(
            @Parameter(description = "Идентификатор проекта", required = true)
            @PositiveOrZero @PathVariable("projectId") Long projectId) {
        return ResponseEntity.ok(projectsService.getById(projectId));
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @Operation(description = "Обновление проекта")
    @PutMapping(
            value = "api/projects/{projectId}"
    )
    public ResponseEntity<ProjectsDto> updateProject(
            @Parameter(description = "Идентификатор проекта", required = true)
            @PositiveOrZero @PathVariable("projectId") Long projectId,
            @Parameter(description = "Запрос на обновление проекта")
            @Valid @RequestPart ProjectsUpdateDto request,
            @RequestParam(required = false) MultipartFile file) throws IOException{
        if (file != null && !file.isEmpty()) {
            request.setPath("https://devcloudy.ru/backimg/" + fileService.saveFile(file, uploadPath));
        } else {
            request.setPath(projectsService.getById(projectId).getPath());
        }
        return ResponseEntity.ok(projectsService.update(projectId, request));
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @Operation(description = "Удаление проекта")
    @DeleteMapping(
            value = "api/projects/{projectId}"
    )
    public ResponseEntity<Void> deleteProject(
            @Parameter(description = "Идентификатор проекта для удаления", required = true)
            @PositiveOrZero @PathVariable("projectId") Long projectId) {
        projectsService.deleteById(projectId);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @Operation(description = "Создание проекта")
    @PostMapping(
            value = "api/projects",
            consumes = {"multipart/form-data"}
    )
    public ResponseEntity<ProjectsDto> createProject(
            @Parameter(description = "Запрос на создание проекта")
            @RequestPart ProjectsCreateDto request,
            @RequestParam(value = "file") MultipartFile file) throws IOException {
        if (file != null) {
            request.setPath("https://devcloudy.ru/backimg/" + fileService.saveFile(file, uploadPath));
        }
        return new ResponseEntity<>(projectsService.create(request), HttpStatus.CREATED);
    }

}
