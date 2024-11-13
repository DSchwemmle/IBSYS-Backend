package com.ibsys.ibsysbackend.services;

import com.ibsys.ibsysbackend.dto.ArticleDto;
import com.ibsys.ibsysbackend.entities.Article;
import com.ibsys.ibsysbackend.repositories.ArticleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@AllArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;

    public List<Article> findAllArticles() {
        return articleRepository.findAll();
    }

    public Article findArticleById(final Integer id) {
        return articleRepository.findById(id).orElse(null);
    }

    public List<Article> findArticlesByNumber(final String[] numbers) {
        List<Article> results = new ArrayList<>();
        Arrays.stream(numbers).toList().forEach(number -> {
            var article = articleRepository.findByNumber(number);
            article.ifPresent(results::add);
        });
        return results;
    }

    public void createArticle(final ArticleDto articleDto) {
        var article = articleDto.toArticle();
        articleRepository.save(article);
    }

    public void createMultipleArticles(final List<ArticleDto> articleDtoList) {
        var articles = articleDtoList.stream().map(ArticleDto::toArticle).toList();
        articleRepository.saveAll(articles);
    }
}
