package com.example.webhub.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "projects")
public class Projects {
    
    @Column(name = "ID")
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String path;
    private String title;
    private String description;
    private LocalDate date;
    private String customer;
    @Lob
    @Column(name = "code", columnDefinition = "text")
    private String html;
    private String link;


}
