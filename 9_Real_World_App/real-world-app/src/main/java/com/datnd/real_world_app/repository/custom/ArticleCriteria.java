package com.datnd.real_world_app.repository.custom;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.datnd.real_world_app.entity.Article;
import com.datnd.real_world_app.model.article.dto.ArticleDTOFilter;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ArticleCriteria {
    private final EntityManager em;

    public Map<String, Object> findAll(ArticleDTOFilter filter) {
        StringBuilder query = new StringBuilder(
                "select a from Article a left join a.author au left join a.userFavorited ufa where 1 = 1");

        Map<String, Object> params = new HashMap<>();

        if (filter.getTag() != null) {
            query.append(" and a.tagList like :tag");
            params.put("tag", "%" + filter.getTag() + "%");
        }
        if (filter.getAuthor() != null) {
            query.append(" and au.username = :author");
            params.put("author", filter.getAuthor());
        }
        if (filter.getFavorited() != null) {
            query.append(" and ufa.username = :favorited");
            params.put("favorited", filter.getFavorited());
        }

        TypedQuery<Article> tQuery = em.createQuery(query.toString(), Article.class);
        Query coutQuery = em.createQuery(query.toString().replace("select a", "select count(a.id)"));

        params.forEach((k, v) -> {
            tQuery.setParameter(k, v);
            coutQuery.setParameter(k, v);
        });

        tQuery.setFirstResult(filter.getOffset());
        tQuery.setMaxResults(filter.getLimit());
        long totalArticles = (long) coutQuery.getSingleResult();
        List<Article> listArticles = tQuery.getResultList();

        Map<String, Object> results = new HashMap<>();
        results.put("listArticles", listArticles);
        results.put("totalArticles", totalArticles);

        return results;

    }

}
