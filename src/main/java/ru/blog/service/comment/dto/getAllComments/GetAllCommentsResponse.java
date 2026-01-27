package ru.blog.service.comment.dto.getAllComments;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.blog.entity.comment.Comment;

import java.util.List;

@Data
@AllArgsConstructor
public class GetAllCommentsResponse {
    private List<Comment> comments;
}
