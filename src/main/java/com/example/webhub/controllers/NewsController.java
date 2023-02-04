package com.example.webhub.controllers;


import com.example.webhub.news.NewsCreateDto;
import com.example.webhub.news.NewsDto;
import com.example.webhub.news.NewsUpdateDto;
import com.example.webhub.services.FileService;
import com.example.webhub.services.NewsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "News Controller", description = "API контролера по работе с нашими новостями")
public class NewsController {

    private final NewsService newsService;
    private final FileService fileService;

    @Value("${upload.path}")
    private String uploadPath;

    public NewsController(NewsService newsService, FileService fileService) {
        this.newsService = newsService;
        this.fileService = fileService;
    }

    @Operation(description = "Получение списка новостей")
    @GetMapping(
            value = "api/getNews",
            produces = {"application/json"}
    )
    public ResponseEntity<List<NewsDto>> getNews() {
        return ResponseEntity.ok(newsService.getNews());
    }


    @Operation(description = "Получение новости")
    @GetMapping(
            value = "api/news/{newsId}",
            produces = {"application/json"}
    )
    public ResponseEntity<NewsDto> getNew(
            @Parameter(description = "Идентификатор новости", required = true)
            @PositiveOrZero @PathVariable("newsId") Long newsId) {
        return ResponseEntity.ok(newsService.getById(newsId));
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @Operation(description = "Обновление новости")
    @PutMapping(
            value = "api/news/{newsId}",
            consumes = {"multipart/form-data"}
    )
    public ResponseEntity<NewsDto> updateNews(
            @Parameter(description = "Идентификатор новости", required = true)
            @PositiveOrZero @PathVariable("newsId") Long newsId,
            @Parameter(description = "Запрос на обновление новости")
            @Valid @RequestPart NewsUpdateDto request,
            @RequestParam(required = false) MultipartFile file) throws IOException {

        if (file != null && !file.isEmpty()) {
            request.setImg("https://devcloudy.ru/backimg/" + fileService.saveFile(file, uploadPath));
        } else {
            request.setImg(newsService.getById(newsId).getImg());
        }
        return ResponseEntity.ok(newsService.update(newsId, request));
    }


    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @Operation(description = "Удаление новости")
    @DeleteMapping(
            value = "api/news/{newsId}"
    )

    public ResponseEntity<Void> deleteNews(
            @Parameter(description = "Идентификатор новости для удаления", required = true)
            @PositiveOrZero @PathVariable("newsId") Long newsId) {
        newsService.deleteById(newsId);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @Operation(description = "Создание новости")
    @PostMapping(
            value = "api/news",
            consumes = {"multipart/form-data"}
    )
    public ResponseEntity<NewsDto> createNews(
            @Parameter(description = "Запрос на создание новости")
            @RequestPart NewsCreateDto request,
            @RequestParam(value = "file") MultipartFile file) throws IOException {
        if (file != null && !file.isEmpty()) {
            request.setImg("https://devcloudy.ru/backimg/" + fileService.saveFile(file, uploadPath));
        }
        return new ResponseEntity<>(newsService.create(request), HttpStatus.CREATED);
    }


}
