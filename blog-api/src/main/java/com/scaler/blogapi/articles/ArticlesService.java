package com.scaler.blogapi.articles;

import com.scaler.blogapi.articles.dto.ArticleResponseDTO;
import com.scaler.blogapi.articles.dto.CreateArticleDTO;
import com.scaler.blogapi.articles.dto.UpdateArticleDTO;
import com.scaler.blogapi.users.UserEntity;
import com.scaler.blogapi.users.UsersRepository;
import com.scaler.blogapi.users.UsersService;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class ArticlesService {

    private final ArticlesRepository articlesRepository;
    private final UsersRepository usersRepository;
    private final ModelMapper modelMapper;

    public ArticlesService(ArticlesRepository articlesRepository, ModelMapper modelMapper, UsersRepository usersRepository) {
        this.articlesRepository = articlesRepository;
        this.modelMapper = modelMapper;
        this.usersRepository = usersRepository;
    }

    public ArticleResponseDTO createArticle(String username, CreateArticleDTO createArticleDTO) {
        UserEntity userEntity = usersRepository.findByUsername(username);
        if (userEntity == null) {
            throw new UsersService.UserNotFoundException(username);
        } else {
            ArticleEntity articleEntity = modelMapper.map(createArticleDTO, ArticleEntity.class);
            articleEntity.setAuthor(userEntity);
            ArticleEntity savedArticle = articlesRepository.save(articleEntity);
            return modelMapper.map(savedArticle, ArticleResponseDTO.class);
        }
    }

    public ArticleResponseDTO updateArticle(String slug, UpdateArticleDTO updateArticleDTO) {
        ArticleEntity articleEntity = articlesRepository.findArticleEntityBySlugEquals(slug);
        if (articleEntity == null) {
            throw new ArticleNotFoundException(slug);
        } else {
            if (updateArticleDTO.getTitle() != null) {
                articleEntity.setTitle(updateArticleDTO.getTitle());
            }
            if (updateArticleDTO.getSubtitle() != null) {
                articleEntity.setSubtitle(updateArticleDTO.getSubtitle());
            }
            if (updateArticleDTO.getBody() != null) {
                articleEntity.setBody(updateArticleDTO.getBody());
            }
            ArticleEntity savedArticle = articlesRepository.save(articleEntity);
            return modelMapper.map(savedArticle, ArticleResponseDTO.class);
        }
    }

    public List<ArticleResponseDTO> getAllArticles() {
        List<ArticleEntity> articleEntities = articlesRepository.findAll();
        List<ArticleResponseDTO> articleResponseDTOS =
                articleEntities
                        .stream()
                        .map(article -> modelMapper.map(article, ArticleResponseDTO.class))
                        .collect(Collectors.toList());

        return articleResponseDTOS;

    }

    public ArticleResponseDTO getArticleBySlug(String slug) {
        ArticleEntity articleEntity = articlesRepository.findArticleEntityBySlugEquals(slug);
        if (articleEntity == null) {
            throw new ArticleNotFoundException(slug);
        }
        ArticleResponseDTO articleResponseDTO = modelMapper.map(articleEntity, ArticleResponseDTO.class);
        return articleResponseDTO;
    }

    public static class ArticleNotFoundException extends IllegalArgumentException {

        public ArticleNotFoundException(String slug) {
            super("User with slug " + slug + " not found");
        }
    }
}
