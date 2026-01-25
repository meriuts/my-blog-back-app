package ru.blog.service.comment.dto.getComments;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetCommentsRequest {
    private Long postId;
}
