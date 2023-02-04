package com.example.webhub.services;

import com.example.webhub.entity.News;
import com.example.webhub.mail.DefaultEmailService;
import com.example.webhub.mappers.NewsMapper;
import com.example.webhub.news.NewsCreateDto;
import com.example.webhub.news.NewsDto;
import com.example.webhub.news.NewsUpdateDto;
import com.example.webhub.repository.NewsRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Data
@Slf4j
@Service
@RequiredArgsConstructor
public class NewsService {

    private final NewsMapper newsMapper;
    private final NewsRepository repository;
    private DefaultEmailService emailService;


    private static final int DEFAULT_PAGE_SIZE = 10;

    public List<NewsDto> getNews(Integer limit) {
        return repository.findAll(PageRequest.of(0, limit == null ? DEFAULT_PAGE_SIZE : limit)).stream()
                .map(newsMapper::newsToNewsDto).collect(Collectors.toList());
    }

    public List<NewsDto> getByTitleLike(String title) {
        return repository.findByTitleLike(title).stream()
                .map(newsMapper::newsToNewsDto).collect(Collectors.toList());
    }

    public NewsDto getById(Long newsId) {
        return newsMapper.newsToNewsDto(repository.findById(newsId).orElse(null));
    }

    public NewsDto update(Long newsId, NewsUpdateDto request) {
        request.setDate(LocalDate.now());
        News news = newsMapper.newsUpdateRequestToNewsView(request, newsId);
        repository.save(news);
        return newsMapper.newsToNewsDto(news);
    }

    public void deleteById(Long newsId) {
        repository.deleteById(newsId);
    }

    public NewsDto create(NewsCreateDto request) {
        log.info("Создаю новость");
        request.setDate(LocalDate.now());
        News news = newsMapper.toNews(request);

        repository.save(news);
        return newsMapper.newsToNewsDto(news);
    }

    public List<NewsDto> getNews() {
        return StreamSupport.stream(repository.findAll().spliterator(), false)
                .map(newsMapper::newsToNewsDto).collect(Collectors.toList());
    }
}
