package ru.blog.service.comment;

import org.springframework.stereotype.Component;
import ru.blog.entity.comment.Comment;
import ru.blog.service.comment.dto.addComment.AddCommentResponse;
import ru.blog.service.comment.dto.getAllComments.GetAllCommentsResponse;
import ru.blog.service.comment.dto.getComment.GetCommentsResponse;
import ru.blog.service.comment.dto.updateComment.UpdateCommentResponse;

import java.util.List;

@Component
public class CommentAssembler {

    public GetAllCommentsResponse assembleGetAllCommentsResponse(List<Comment> comment) {
        return new GetAllCommentsResponse(comment);
    }

    public GetCommentsResponse assembleGetCommentsResponse(Comment comment) {
        return new GetCommentsResponse(comment);
    }

    public AddCommentResponse assembleAddCommentsResponse(Comment comment) {
        return new AddCommentResponse(comment);
    }

    public UpdateCommentResponse assembleUpdateCommentResponse(Comment comment) {
        return new UpdateCommentResponse(
                comment.getId(),
                comment.getText(),
                comment.getPostId()
        );
    }

}
