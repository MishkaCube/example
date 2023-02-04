package com.example.webhub.controllers;

import com.example.webhub.article.ArticleCreateDto;
import com.example.webhub.article.ArticleDto;
import com.example.webhub.article.ArticleUpdateDto;
import com.example.webhub.services.ArticleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin("https://admin.devcloudy.ru")
@Tag(name = "Articles Controller", description = "API контролера по работе с нашими статьями")

public class ArticleController {

    private final ArticleService articleService;

    @Operation(description = "Получение списка статей")
    @GetMapping(
            value = "api/get_articles",
            produces = {"application/json"}
    )
    public ResponseEntity<List<ArticleDto>> getArticles() {
        return ResponseEntity.ok(articleService.getArticles());
    }

    @Operation(description = "Получение статьи")
    @GetMapping(
            value = "api/articles/{artId}",
            produces = {"application/json"}
    )
    public ResponseEntity<ArticleDto> getArticle(
            @Parameter(description = "Идентификатор статьи", required = true)
            @PositiveOrZero @PathVariable("artId") Long artId) {
        return ResponseEntity.ok(articleService.getById(artId));
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @Operation(description = "Обновление статьи")
    @PutMapping(
            value = "api/articles/{artId}"
    )
    public ResponseEntity<ArticleDto> updateArticle(
            @Parameter(description = "Идентификатор статьи", required = true)
            @PositiveOrZero @PathVariable("artId") Long artId,
            @Parameter(description = "Запрос на обновление статьи")
            @Valid @RequestBody(required = false) ArticleUpdateDto request) {
        return ResponseEntity.ok(articleService.update(artId, request));
    }


    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @Operation(description = "Удаление статьи")
    @DeleteMapping(
            value = "api/articles/{artId}"
    )
    public ResponseEntity<Void> deleteArticle(
            @Parameter(description = "Идентификатор статьи для удаления", required = true)
            @PositiveOrZero @PathVariable("artId") Long artId) {
        articleService.deleteById(artId);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @Operation(description = "Создание статьи")
    @PostMapping(
            value = "api/articles",
            produces = {"application/json"},
            consumes = {"application/json"}
    )
    public ResponseEntity<ArticleDto> createArticle(
            @Parameter(description = "Запрос на создание статьи")
            @Valid @RequestBody ArticleCreateDto request) {
        return new ResponseEntity<>(articleService.create(request), HttpStatus.CREATED);
    }

}
