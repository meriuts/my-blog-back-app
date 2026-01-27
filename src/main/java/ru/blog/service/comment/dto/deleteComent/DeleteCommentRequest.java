package ru.blog.service.comment.dto.deleteComent;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeleteCommentRequest {
    private Long postId;
    private Long commentId;
}
