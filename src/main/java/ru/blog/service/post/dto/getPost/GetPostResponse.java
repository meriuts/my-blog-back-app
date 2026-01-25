package ru.blog.service.post.dto.getPost;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetPostResponse {
    private Long id;
    private String title;
    private String text;
    private String[] tags;
    private Integer likesCount;
    private Integer commentsCount;
}
