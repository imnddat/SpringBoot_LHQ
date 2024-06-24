package com.datnd.real_world_app.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.datnd.real_world_app.entity.Article;
import com.datnd.real_world_app.entity.User;
import com.datnd.real_world_app.model.article.dto.ArticleDTOCreate;
import com.datnd.real_world_app.model.article.dto.ArticleDTOResponse;
import com.datnd.real_world_app.model.article.mapper.ArticleMapper;
import com.datnd.real_world_app.repository.ArticleRepository;
import com.datnd.real_world_app.service.ArticleService;
import com.datnd.real_world_app.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {
    private final ArticleRepository articleRepository;
    private final UserService userService;

    @Override
    public Map<String, ArticleDTOResponse> createArticle(Map<String, ArticleDTOCreate> articleDTOCreateMap) {
        ArticleDTOCreate articleDTOCreate = articleDTOCreateMap.get("article");

        Article article = ArticleMapper.toArticle(articleDTOCreate);
        User currentUser = userService.getUserLoggedIn();

        article.setAuthor(currentUser);

        article = articleRepository.save(article);

        Map<String, ArticleDTOResponse> wrapper = new HashMap<>();
        ArticleDTOResponse articleDTOResponse = ArticleMapper.toArticleDTOResponse(article, false, 0, false);
        wrapper.put("article", articleDTOResponse);
        return wrapper;
    }

    @Override
    public Map<String, ArticleDTOResponse> getArticleBySlug(String slug) {
        Article article = articleRepository.findBySlug(slug);

        Map<String, ArticleDTOResponse> wrapper = new HashMap<>();

        User userLoggedIn = userService.getUserLoggedIn();

        User author = article.getAuthor();
        
        Set<User> followers = author.getFollowers();
        boolean isFollowing = false;
        for (User u : followers) {
            if (u.getId() == userLoggedIn.getId()) {
                isFollowing = true;
                break;
            }
        }
        ArticleDTOResponse articleDTOResponse = ArticleMapper.toArticleDTOResponse(article, false, 0, isFollowing);
        wrapper.put("article", articleDTOResponse);
        return wrapper;
    }

}
