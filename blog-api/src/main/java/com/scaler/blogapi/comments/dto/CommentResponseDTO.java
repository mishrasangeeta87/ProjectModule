package com.scaler.blogapi.comments.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentResponseDTO {
    Integer id;
    String title;
    String body;
}
