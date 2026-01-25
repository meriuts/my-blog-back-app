package ru.blog.service.post.dto.search;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.blog.entity.post.Post;

import java.util.List;

@Data
@AllArgsConstructor
public class SearchPostResponse {
    private List<Post> posts;
    private Boolean hasPrev;
    private Boolean hasNext;
    private Integer lastPage;
}