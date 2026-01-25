package ru.blog.service.comment.dto.getComments;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.blog.entity.comment.Comment;

import java.util.List;

@Data
@AllArgsConstructor
public class GetCommentsResponse {
    private List<Comment> comments;
}
