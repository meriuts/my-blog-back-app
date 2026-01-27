package ru.blog.service.post.dto.getPhoto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetPostPhotoRequest {
    private Long postId;
}
