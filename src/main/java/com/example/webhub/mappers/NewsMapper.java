package com.example.webhub.mappers;

import com.example.webhub.entity.News;
import com.example.webhub.news.NewsCreateDto;
import com.example.webhub.news.NewsDto;
import com.example.webhub.news.NewsUpdateDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NewsMapper {
    NewsDto newsToNewsDto(News entity);

    News newsUpdateRequestToNewsView(NewsUpdateDto dto, Long id);

    News toNews(NewsCreateDto dto);

}
