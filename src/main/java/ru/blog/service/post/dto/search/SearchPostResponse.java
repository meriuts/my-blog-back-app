package ru.blog.service.post.dto.search;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.blog.service.post.dto.getPost.GetPostResponse;

import java.util.List;

@Data
@AllArgsConstructor
public class SearchPostResponse {
    private List<GetPostResponse> posts;
    private Boolean hasPrev;
    private Boolean hasNext;
    private Integer lastPage;
}