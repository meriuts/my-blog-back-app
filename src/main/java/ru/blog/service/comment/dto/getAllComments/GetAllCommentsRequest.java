package ru.blog.service.comment.dto.getAllComments;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetAllCommentsRequest {
    private Long postId;
}
