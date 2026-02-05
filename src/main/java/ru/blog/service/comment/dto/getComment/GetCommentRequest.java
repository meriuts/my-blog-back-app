package ru.blog.service.comment.dto.getComment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetCommentRequest {
    private Long postId;
    private Long commentId;
}
