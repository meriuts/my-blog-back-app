package ru.blog.service.post.dto.createPost;

import lombok.Data;

@Data
public class CreatePostRequest {
    private String title;
    private String text;
    private String[] tags;
}


