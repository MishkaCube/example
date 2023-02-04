package com.example.webhub.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "news")
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String img;

    private String title;

    private LocalDate date;

    private String description;

    @Lob
    @Column(columnDefinition = "text")
    private String html;
    private String link;


}
