package com.example.webhub.article;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class ArticleDto {

    private Long id;
    private String title;
    private String text;
    private String html;
    private String link;


}
