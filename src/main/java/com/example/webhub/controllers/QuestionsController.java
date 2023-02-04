package com.example.webhub.controllers;

import com.example.webhub.questions.QuestionCreateDto;
import com.example.webhub.questions.QuestionUpdateDto;
import com.example.webhub.questions.QuestionsDto;
import com.example.webhub.services.QuestionService;
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
@Tag(name = "Question Controller", description = "API контролера по работе с вопросами пользователей")
public class QuestionsController {

    private final QuestionService questionService;


    @Operation(description = "Получение списка вопросов")
    @GetMapping(
            value = "api/get_questions",
            produces = {"application/json"}
    )
    public ResponseEntity<List<QuestionsDto>> getQuestions() {
        return ResponseEntity.ok(questionService.getQuestions());
    }

    @Operation(description = "Получение вопроса")
    @GetMapping(
            value = "api/questions/{questId}",
            produces = {"application/json"}
    )
    public ResponseEntity<QuestionsDto> getQuestion(
            @Parameter(description = "Идентификатор вопроса", required = true)
            @PositiveOrZero @PathVariable("questId") Long questId) {
        return ResponseEntity.ok(questionService.getById(questId));
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @Operation(description = "Обновление вопроса")
    @PutMapping(
            value = "api/questions/{questId}"
    )
    public ResponseEntity<QuestionsDto> updateQuestion(
            @Parameter(description = "Идентификатор вопроса", required = true)
            @PositiveOrZero @PathVariable("questId") Long questId,
            @Parameter(description = "Запрос на обновление вопроса")
            @Valid @RequestBody(required = false) QuestionUpdateDto request) {
        return ResponseEntity.ok(questionService.update(questId, request));
    }


    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @Operation(description = "Удаление вопроса")
    @DeleteMapping(
            value = "api/questions/{questId}"
    )
    public ResponseEntity<Void> deleteQuestion(
            @Parameter(description = "Идентификатор вопроса для удаления", required = true)
            @PositiveOrZero @PathVariable("questId") Long questId) {
        questionService.deleteById(questId);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @Operation(description = "Создание вопроса")
    @PostMapping(
            value = "api/questions",
            produces = {"application/json"},
            consumes = {"application/json"}
    )
    public ResponseEntity<QuestionsDto> createQuestion(
            @Parameter(description = "Запрос на создание вопроса")
            @Valid @RequestBody QuestionCreateDto request) {
        return new ResponseEntity<>(questionService.create(request), HttpStatus.CREATED);
    }
}
