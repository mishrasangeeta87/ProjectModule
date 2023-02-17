package com.scaler.blogapi.articles;

import com.scaler.blogapi.articles.dto.ArticleResponseDTO;
import com.scaler.blogapi.articles.dto.CreateArticleDTO;
import com.scaler.blogapi.articles.dto.UpdateArticleDTO;
import com.scaler.blogapi.users.UserEntity;
import com.scaler.blogapi.users.UsersRepository;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class ArticlesServiceTests {
    @Autowired
    ArticlesRepository articlesRepository;
    @Autowired
    UsersRepository usersRepository;
    ArticlesService articlesService;

    private ArticlesService createArticlesService() {
        if (articlesService == null) {
            articlesService = new ArticlesService(articlesRepository, new ModelMapper(), usersRepository);
        }
        return articlesService;
    }

    @Test
    public void testCreateArticle() {
        createArticlesService();
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("Sangee");
        userEntity.setEmail("sangee@gmail.com");
        userEntity.setPassword("hashedpwd");
        UserEntity savedUser = usersRepository.save(userEntity);

        CreateArticleDTO createArticleDTO = new CreateArticleDTO();
        createArticleDTO.setBody("This is my test article");
        createArticleDTO.setSlug("test-article");
        createArticleDTO.setSubtitle("test subtitle");
        createArticleDTO.setTitle("Test title");

        ArticleResponseDTO savedArticle = articlesService.createArticle(savedUser.getUsername(), createArticleDTO);
        assertNotNull(savedArticle);
        assertEquals("This is my test article", savedArticle.getBody());
    }

    @Test
    public void testGetAllArticles() {
        createArticlesService();
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("Sangee");
        userEntity.setEmail("sangee@gmail.com");
        userEntity.setPassword("hashedpwd");
        UserEntity savedUser = usersRepository.save(userEntity);

        CreateArticleDTO createArticleDTO = new CreateArticleDTO();
        createArticleDTO.setBody("This is my test article");
        createArticleDTO.setSlug("test-article");
        createArticleDTO.setSubtitle("test subtitle");
        createArticleDTO.setTitle("Test title");

        ArticleResponseDTO savedArticle = articlesService.createArticle(savedUser.getUsername(), createArticleDTO);

        List<ArticleResponseDTO> articleResponseDTOList = articlesService.getAllArticles();
        assertEquals(1, articleResponseDTOList.size());
    }

    @Test
    public void testGetArticleBySlug() {
        createArticlesService();
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("Sangee");
        userEntity.setEmail("sangee@gmail.com");
        userEntity.setPassword("hashedpwd");
        UserEntity savedUser = usersRepository.save(userEntity);

        CreateArticleDTO createArticleDTO = new CreateArticleDTO();
        createArticleDTO.setBody("This is my test article");
        createArticleDTO.setSlug("test-slug");
        createArticleDTO.setSubtitle("test subtitle");
        createArticleDTO.setTitle("Test title");

        articlesService.createArticle(savedUser.getUsername(), createArticleDTO);

        ArticleResponseDTO articleResponseDTO = articlesService.getArticleBySlug("test-slug");
        assertEquals("test-slug", articleResponseDTO.getSlug());
    }

    @Test
    public void testGetArticleBySlugNonExistent() {
        createArticlesService();
        assertThrows(ArticlesService.ArticleNotFoundException.class, () -> articlesService.getArticleBySlug("test-non-existent-slug"));
    }

    @Test
    public void testUpdateArticle() {
        createArticlesService();
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("Sangee");
        userEntity.setEmail("sangee@gmail.com");
        userEntity.setPassword("hashedpwd");
        UserEntity savedUser = usersRepository.save(userEntity);

        CreateArticleDTO createArticleDTO = new CreateArticleDTO();
        createArticleDTO.setBody("This is my test article");
        createArticleDTO.setSlug("test-slug");
        createArticleDTO.setSubtitle("test subtitle");
        createArticleDTO.setTitle("Test title");
        articlesService.createArticle(savedUser.getUsername(), createArticleDTO);

        UpdateArticleDTO updateArticleDTO = new UpdateArticleDTO();
        updateArticleDTO.setSubtitle("test subtitle updated");

        ArticleResponseDTO articleResponseDTO = articlesService.updateArticle("test-slug", updateArticleDTO);
        assertEquals("test subtitle updated", articleResponseDTO.getSubtitle());
    }
}
