package ru.blog.service.comment.dto.getComment;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetCommentRequest {
    private Long postId;
    private Long commentId;
}
