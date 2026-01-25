package ru.blog.service.post.dto.update;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UpdatePostRequest {
    private Long id;
    private String title;
    private String text;
    private String[] tags;
}
