package ru.blog.service.comment.dto.getComment;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetCommentsResponse {
    private Long id;
    private String text;
    private Long postId;
}
