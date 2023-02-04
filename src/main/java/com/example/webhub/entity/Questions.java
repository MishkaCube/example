package com.example.webhub.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "questions")
public class Questions {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String question;
    private String answer;

}
