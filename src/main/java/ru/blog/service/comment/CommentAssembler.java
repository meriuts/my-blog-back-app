package ru.blog.service.comment;

import org.springframework.stereotype.Component;
import ru.blog.entity.comment.Comment;
import ru.blog.service.comment.dto.getComments.GetCommentsResponse;

import java.util.List;

@Component
public class CommentAssembler {

    public GetCommentsResponse assembleGetCommentsResponse(List<Comment> comment) {
        return new GetCommentsResponse(comment);
    }

}
