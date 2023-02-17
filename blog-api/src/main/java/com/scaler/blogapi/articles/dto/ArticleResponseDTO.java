package com.scaler.blogapi.articles.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArticleResponseDTO {
    Integer id;
    String slug;
    String title;
    String subtitle;
    String body;
}
