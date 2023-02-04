package com.example.webhub.news;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Запрос на обновление задачи")
public class NewsUpdateDto {

    @NotNull
    private String img;
    @NotNull
    private String title;
    @NotNull
    private LocalDate date;
    @NotNull
    private String description;
    @NotNull
    private String html;
    @NotNull
    private String link;

}
