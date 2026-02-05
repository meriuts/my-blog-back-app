package ru.blog.service.comment.dto.addComment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddCommentRequest {
    private String text;
    private Long postId;

}
