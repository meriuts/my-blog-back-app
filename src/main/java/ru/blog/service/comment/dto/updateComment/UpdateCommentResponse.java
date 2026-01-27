package ru.blog.service.comment.dto.updateComment;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UpdateCommentResponse {
    private Long id;
    private String text;
    private Long postId;
}
