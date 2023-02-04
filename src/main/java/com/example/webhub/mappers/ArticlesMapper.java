package com.example.webhub.mappers;

import com.example.webhub.article.ArticleCreateDto;
import com.example.webhub.article.ArticleDto;
import com.example.webhub.article.ArticleUpdateDto;
import com.example.webhub.entity.Article;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ArticlesMapper {

    ArticleDto articleToArticleDto(Article entity);

    Article articleUpdateRequestToArticleView(ArticleUpdateDto dto, Long id);

    Article toArticle(ArticleCreateDto dto);
}
