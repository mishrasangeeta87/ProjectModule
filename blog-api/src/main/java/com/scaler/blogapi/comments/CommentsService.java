package com.scaler.blogapi.comments;

import com.scaler.blogapi.articles.ArticleEntity;
import com.scaler.blogapi.articles.ArticlesRepository;
import com.scaler.blogapi.articles.ArticlesService;
import com.scaler.blogapi.comments.dto.CommentResponseDTO;
import com.scaler.blogapi.comments.dto.CreateCommentDTO;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CommentsService {
    private CommentsRepository commentsRepository;
    private ModelMapper modelMapper;
    private ArticlesRepository articlesRepository;

    public CommentsService(CommentsRepository commentsRepository, ModelMapper modelMapper, ArticlesRepository articlesRepository) {
        this.commentsRepository = commentsRepository;
        this.modelMapper = modelMapper;
        this.articlesRepository = articlesRepository;
    }

    public CommentResponseDTO createComment(String slug, CreateCommentDTO createCommentDTO) {
        ArticleEntity articleEntity = articlesRepository.findArticleEntityBySlugEquals(slug);
        if (articleEntity == null) {
            throw new ArticlesService.ArticleNotFoundException(slug);
        } else {
            CommentEntity commentEntity = modelMapper.map(createCommentDTO, CommentEntity.class);
            commentEntity.setArticle(articleEntity);
            CommentEntity savedComment = commentsRepository.save(commentEntity);
            return modelMapper.map(savedComment, CommentResponseDTO.class);
        }
    }

    public CommentResponseDTO deleteComment(String slug, Integer commentId) {
        ArticleEntity articleEntity = articlesRepository.findArticleEntityBySlugEquals(slug);
        if (articleEntity == null) {
            throw new ArticlesService.ArticleNotFoundException(slug);
        } else {
            Optional<CommentEntity> commentEntity = commentsRepository.findById(commentId);
            if (commentEntity.isEmpty()) {
                throw new CommentNotFoundException(commentId);
            } else {
                commentsRepository.delete(commentEntity.get());
                return modelMapper.map(commentEntity.get(), CommentResponseDTO.class);
            }
        }
    }

    public List<CommentResponseDTO> getAllComments(String slug) {
        ArticleEntity articleEntity = articlesRepository.findArticleEntityBySlugEquals(slug);
        if (articleEntity == null) {
            throw new ArticlesService.ArticleNotFoundException(slug);
        } else {
            List<CommentEntity> commentEntities = commentsRepository.findAllByArticleEquals(articleEntity);
            return commentEntities.stream()
                    .map(commentEntity -> modelMapper.map(commentEntity, CommentResponseDTO.class))
                    .collect(Collectors.toList());
        }
    }

    public static class CommentNotFoundException extends IllegalArgumentException {
        public CommentNotFoundException(Integer id) {
            super("Comment with id " + id + " not found");
        }
    }

}
