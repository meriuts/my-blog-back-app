package ru.blog.service.post.dto.createPost;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreatePostResponse {
    private String title;
    private String text;
    private String[] tags;
    private Integer likesCount;
    private Integer commentsCount;
}
