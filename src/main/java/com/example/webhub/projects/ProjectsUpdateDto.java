package com.example.webhub.projects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectsUpdateDto {

    private String path;
    private String title;
    private String description;
    private LocalDate date;
    private String customer;
    private String html;
    private String link;

}
