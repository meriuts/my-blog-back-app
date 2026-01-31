package ru.blog.service.post.dto.update;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePostRequest {
    private Long id;
    private String title;
    private String text;
    private List<String> tags;
}
