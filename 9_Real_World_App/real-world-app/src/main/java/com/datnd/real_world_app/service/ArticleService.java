package com.datnd.real_world_app.service;

import java.util.Map;

import com.datnd.real_world_app.model.article.dto.ArticleDTOCreate;
import com.datnd.real_world_app.model.article.dto.ArticleDTOResponse;

public interface ArticleService {

    public Map<String, ArticleDTOResponse> createArticle(Map<String, ArticleDTOCreate> articleDTOCreateMap);

    public Map<String, ArticleDTOResponse> getArticleBySlug(String slug);

}
