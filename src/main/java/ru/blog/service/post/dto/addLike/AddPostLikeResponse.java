package ru.blog.service.post.dto.addLike;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddPostLikeResponse {
    private Integer likesCount;
}
