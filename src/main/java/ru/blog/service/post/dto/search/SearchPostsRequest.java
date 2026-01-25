package ru.blog.service.post.dto.search;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SearchPostsRequest {
    private String search;
    private Integer pageNumber;
    private Integer pageSize;
    private Integer offset;
}
