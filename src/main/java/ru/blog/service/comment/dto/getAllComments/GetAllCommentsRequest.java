package ru.blog.service.comment.dto.getAllComments;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllCommentsRequest {
    private Long postId;
}
