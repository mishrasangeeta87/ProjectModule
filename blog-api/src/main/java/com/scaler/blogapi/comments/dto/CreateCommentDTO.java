package com.scaler.blogapi.comments.dto;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CreateCommentDTO {
    String title;
    String body;
}
