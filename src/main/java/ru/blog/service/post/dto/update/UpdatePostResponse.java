package ru.blog.service.post.dto.update;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UpdatePostResponse {
    private String title;
    private String text;
    private String[] tags;
    private Integer likesCount;
    private Integer commentsCount;
}
