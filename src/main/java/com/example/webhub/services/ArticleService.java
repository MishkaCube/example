package com.example.webhub.services;

import com.example.webhub.article.ArticleCreateDto;
import com.example.webhub.article.ArticleDto;
import com.example.webhub.article.ArticleUpdateDto;
import com.example.webhub.entity.Article;
import com.example.webhub.mappers.ArticlesMapper;
import com.example.webhub.repository.ArticleRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Slf4j
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository repository;
    private final ArticlesMapper mapper;

    private static final int DEFAULT_PAGE_SIZE = 10;

    public List<ArticleDto> getArticles(Integer limit) {
        return repository.findAll(PageRequest.of(0, limit == null ? DEFAULT_PAGE_SIZE : limit)).stream()
                .map(mapper::articleToArticleDto).collect(Collectors.toList());
    }

    public List<ArticleDto> getByTitleLike(String titleLike) {
        return repository.findByTitleLike(titleLike).stream()
                .map(mapper::articleToArticleDto).collect(Collectors.toList());
    }

    public ArticleDto getById(Long artId) {
        return mapper.articleToArticleDto(repository.findById(artId).orElse(null));
    }

    public ArticleDto update(Long artId, ArticleUpdateDto request) {
        Article article = mapper.articleUpdateRequestToArticleView(request, artId);
        repository.save(article);
        return mapper.articleToArticleDto(article);
    }

    public void deleteById(Long artId) {
        repository.deleteById(artId);
    }

    public ArticleDto create(ArticleCreateDto request) {
        log.info("Создаю статью");
        Article article = mapper.toArticle(request);
        repository.save(article);
        return mapper.articleToArticleDto(article);
    }

    public List<ArticleDto> getArticles() {
        return StreamSupport.stream(repository.findAll().spliterator(), false)
                .map(mapper::articleToArticleDto).collect(Collectors.toList());
    }
}
