package ru.blog.service.comment.dto.addComment;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddCommentResponse {
    private Long id;
    private String text;
    private Long postId;
}
