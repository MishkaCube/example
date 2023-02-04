package com.example.webhub.mappers;

import com.example.webhub.questions.QuestionCreateDto;
import com.example.webhub.questions.QuestionUpdateDto;
import com.example.webhub.questions.QuestionsDto;
import com.example.webhub.entity.Questions;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface QuestionMapper {
    QuestionsDto questionToQuestionDto(Questions entity);

    Questions questionsUpdateRequestToQuestionsView(QuestionUpdateDto dto, Long id);

    Questions toQuestions(QuestionCreateDto dto);
}
