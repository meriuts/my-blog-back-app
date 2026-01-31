package ru.blog.service.comment.dto.getAllComments;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.blog.service.comment.dto.getComment.GetCommentsResponse;

import java.util.List;

@Data
@AllArgsConstructor
public class GetAllCommentsResponse {
    private List<GetCommentsResponse> comments;
}
