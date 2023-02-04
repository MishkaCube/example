package com.example.webhub.projects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class ProjectsDto {

    private Long id;
    private String path;
    private String title;
    private String description;
    private LocalDate date;
    private String customer;
    private String html;
    private String link;


}
