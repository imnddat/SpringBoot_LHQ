package com.datnd.real_world_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.datnd.real_world_app.entity.Article;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Integer> {

    public Article findBySlug(String slug);

}
