package com.example.webhub.services;

import com.example.webhub.questions.QuestionCreateDto;
import com.example.webhub.questions.QuestionUpdateDto;
import com.example.webhub.questions.QuestionsDto;
import com.example.webhub.entity.Questions;
import com.example.webhub.mappers.QuestionMapper;
import com.example.webhub.repository.QuestionRepository;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Slf4j
public class QuestionService {

    private final QuestionMapper questionMapper;
    private final QuestionRepository repository;

    public QuestionService(QuestionRepository repository, QuestionMapper questionMapper) {
        this.repository = repository;
        this.questionMapper = questionMapper;
    }

    public QuestionsDto getById(Long questId) {
        return questionMapper.questionToQuestionDto(repository.findById(questId).orElse(null));
    }

    public QuestionsDto update(Long questId, QuestionUpdateDto request) {
        Questions questions = questionMapper.questionsUpdateRequestToQuestionsView(request, questId);
        repository.save(questions);
        return questionMapper.questionToQuestionDto(questions);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public QuestionsDto create(QuestionCreateDto request) {
        log.info("Создаю вопрос");
        Questions questions = questionMapper.toQuestions(request);
        repository.save(questions);
        return questionMapper.questionToQuestionDto(questions);
    }

    public List<QuestionsDto> getQuestions() {
        return StreamSupport.stream(repository.findAll().spliterator(), false)
                .map(questionMapper::questionToQuestionDto).collect(Collectors.toList());
    }
}
