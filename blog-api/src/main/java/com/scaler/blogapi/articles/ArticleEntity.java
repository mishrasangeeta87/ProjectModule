package com.scaler.blogapi.articles;

import com.scaler.blogapi.commons.BaseEntity;
import com.scaler.blogapi.users.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "articles")
public class ArticleEntity extends BaseEntity {

    @Column(unique = true, nullable = false, length = 150)
    String slug;
    @Column(nullable = false, length = 200)
    String title;
    String subtitle;
    @Column(nullable = false, length = 8000)
    String body;

    @ManyToOne
    UserEntity author;

    @ManyToMany
    List<UserEntity> likedBy;

}
