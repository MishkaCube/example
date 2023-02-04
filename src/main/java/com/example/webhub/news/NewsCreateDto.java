package com.example.webhub.news;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class NewsCreateDto {

    private String img;
    private String title;
    private LocalDate date;
    private String description;
    private String html;
    private String link;

}
