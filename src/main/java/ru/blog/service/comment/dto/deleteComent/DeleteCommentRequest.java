package ru.blog.service.comment.dto.deleteComent;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteCommentRequest {
    private Long postId;
    private Long commentId;
}
