package com.datnd.real_world_app.model.article.mapper;

import java.util.Date;

import com.datnd.real_world_app.entity.Article;
import com.datnd.real_world_app.entity.User;
import com.datnd.real_world_app.model.article.dto.ArticleDTOCreate;
import com.datnd.real_world_app.model.article.dto.ArticleDTOResponse;
import com.datnd.real_world_app.model.article.dto.AuthorDTOResponse;
import com.datnd.real_world_app.util.SlugUtil;

public class ArticleMapper {

    public static Article toArticle(ArticleDTOCreate articleDTOCreate) {
        Date now = new Date();
        Article article = Article.builder().slug(SlugUtil.getSlug(articleDTOCreate.getTitle()))
                .title(articleDTOCreate.getTitle()).description(articleDTOCreate.getDescription())
                .body(articleDTOCreate.getBody()).createdAt(now).updatedAt(now).build();
        article.setTagList(articleDTOCreate.getTagList());
        return article;
    }

    public static ArticleDTOResponse toArticleDTOResponse(Article article, boolean favorited, int favoritesCount,
            boolean isFollowing) {
        return ArticleDTOResponse.builder().slug(article.getSlug()).title(article.getTitle())
                .description(article.getDescription()).body(article.getBody()).tagList(article.getTagList())
                .createdAt(article.getCreatedAt()).updatedAt(article.getUpdatedAt()).favorited(favorited)
                .favoritesCount(favoritesCount).author(toAuthorDTOResponse(article.getAuthor(), isFollowing)).build();
    }

    private static AuthorDTOResponse toAuthorDTOResponse(User author, boolean isFollowing) {
        return AuthorDTOResponse.builder().username(author.getUsername()).bio(author.getBio()).image(author.getImage())
                .following(isFollowing).build();
    }

}
