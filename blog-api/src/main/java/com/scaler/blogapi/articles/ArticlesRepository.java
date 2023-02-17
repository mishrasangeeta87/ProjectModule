package com.scaler.blogapi.articles;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticlesRepository extends JpaRepository<ArticleEntity,Integer> {
    ArticleEntity findArticleEntityBySlugEquals(String slug);
}
