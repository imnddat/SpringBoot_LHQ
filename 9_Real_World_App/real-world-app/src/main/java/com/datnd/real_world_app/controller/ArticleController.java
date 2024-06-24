package com.datnd.real_world_app.controller;

import org.springframework.web.bind.annotation.RestController;

import com.datnd.real_world_app.model.article.dto.ArticleDTOCreate;
import com.datnd.real_world_app.model.article.dto.ArticleDTOResponse;
import com.datnd.real_world_app.service.ArticleService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/api/articles")
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleService articleService;

    @PostMapping("")
    public Map<String,ArticleDTOResponse> createArticle(@RequestBody Map<String, ArticleDTOCreate> articleDTOCreateMap) {
        return articleService.createArticle(articleDTOCreateMap);
    }

    @GetMapping("/{slug}")
    public Map<String, ArticleDTOResponse> getArticleBySlug(@PathVariable String slug){
        return articleService.getArticleBySlug(slug);
    }
    
}
