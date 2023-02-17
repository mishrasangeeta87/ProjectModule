package com.scaler.blogapi.comments;

import com.scaler.blogapi.articles.ArticleEntity;
import com.scaler.blogapi.articles.ArticlesRepository;
import com.scaler.blogapi.comments.dto.CommentResponseDTO;
import com.scaler.blogapi.comments.dto.CreateCommentDTO;
import com.scaler.blogapi.users.UserEntity;
import com.scaler.blogapi.users.UsersRepository;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class CommentsServiceTests {
    @Autowired
    ArticlesRepository articlesRepository;
    @Autowired
    CommentsRepository commentsRepository;

    @Autowired
    UsersRepository usersRepository;
    CommentsService commentsService;

    private CommentsService createCommentsService() {
        if (commentsService == null) {
            commentsService = new CommentsService(commentsRepository, new ModelMapper(), articlesRepository);
        }
        return commentsService;
    }

    @Test
    public void getAllComments() {
        createCommentsService();

        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("Sangee");
        userEntity.setEmail("sangee@gmail.com");
        userEntity.setPassword("hashedpwd");
        UserEntity savedUser = usersRepository.save(userEntity);

        ArticleEntity articleEntity = new ArticleEntity();
        articleEntity.setBody("This is my test article");
        articleEntity.setSlug("test-article");
        articleEntity.setSubtitle("test subtitle");
        articleEntity.setTitle("Test title");
        articleEntity.setAuthor(savedUser);

        ArticleEntity savedArticle = articlesRepository.save(articleEntity);

        CreateCommentDTO createCommentDTO1 = new CreateCommentDTO();
        createCommentDTO1.setBody("Test comment 1");
        createCommentDTO1.setTitle("Comment title");

        CreateCommentDTO createCommentDTO2 = new CreateCommentDTO();
        createCommentDTO2.setBody("Test comment 1");
        createCommentDTO2.setTitle("Comment title");

        commentsService.createComment(savedArticle.getSlug(), createCommentDTO1);
        commentsService.createComment(savedArticle.getSlug(), createCommentDTO2);

        List<CommentResponseDTO> commentResponseDTOList = commentsService.getAllComments("test-article");
        assertEquals(2, commentResponseDTOList.size());

    }

    @Test
    public void testCreateComment() {
        createCommentsService();
        CreateCommentDTO createCommentDTO = new CreateCommentDTO();
        createCommentDTO.setBody("Test comment");
        createCommentDTO.setTitle("Comment title");

        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("Sangee");
        userEntity.setEmail("sangee@gmail.com");
        userEntity.setPassword("hashedpwd");
        UserEntity savedUser = usersRepository.save(userEntity);

        ArticleEntity articleEntity = new ArticleEntity();
        articleEntity.setBody("This is my test article");
        articleEntity.setSlug("test-article");
        articleEntity.setSubtitle("test subtitle");
        articleEntity.setTitle("Test title");
        articleEntity.setAuthor(savedUser);

        ArticleEntity savedArticle = articlesRepository.save(articleEntity);

        CommentResponseDTO commentResponseDTO = commentsService.createComment(savedArticle.getSlug(), createCommentDTO);

        assertNotNull(commentResponseDTO.getId());
    }

    @Test
    public void deleteCommentEntity() {
        createCommentsService();

        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("Sangee");
        userEntity.setEmail("sangee@gmail.com");
        userEntity.setPassword("hashedpwd");
        UserEntity savedUser = usersRepository.save(userEntity);

        ArticleEntity articleEntity = new ArticleEntity();
        articleEntity.setBody("This is my test article");
        articleEntity.setSlug("test-article-slug");
        articleEntity.setSubtitle("test subtitle");
        articleEntity.setTitle("Test title");
        articleEntity.setAuthor(savedUser);

        ArticleEntity savedArticle = articlesRepository.save(articleEntity);

        CreateCommentDTO createCommentDTO1 = new CreateCommentDTO();
        createCommentDTO1.setBody("Test comment 1");
        createCommentDTO1.setTitle("Comment title 1");

        CreateCommentDTO createCommentDTO2 = new CreateCommentDTO();
        createCommentDTO2.setBody("Test comment 1");
        createCommentDTO2.setTitle("Comment title 2");

        CommentResponseDTO dto1 = commentsService.createComment(savedArticle.getSlug(), createCommentDTO1);
        CommentResponseDTO dto2 = commentsService.createComment(savedArticle.getSlug(), createCommentDTO2);

        CommentResponseDTO dtoDeleted = commentsService.deleteComment("test-article-slug", dto1.getId());
        assertEquals("Comment title 1", dtoDeleted.getTitle());
    }
}
