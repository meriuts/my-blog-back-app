package ru.blog.service.post.dto.getPhoto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetPostPhotoRequest {
    private Long postId;
}
