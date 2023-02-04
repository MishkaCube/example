package com.example.webhub.questions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class QuestionsDto {

    private Long id;
    private String question;
    private String answer;

}
