package ru.blog.service.comment;

import org.springframework.stereotype.Component;
import ru.blog.entity.comment.Comment;
import ru.blog.service.comment.dto.addComment.AddCommentResponse;
import ru.blog.service.comment.dto.getAllComments.GetAllCommentsResponse;
import ru.blog.service.comment.dto.getComment.GetCommentsResponse;
import ru.blog.service.comment.dto.updateComment.UpdateCommentResponse;

import java.util.List;

@Component
public class CommentResponseAssembler {

    public GetAllCommentsResponse assembleGetAllCommentsResponse(List<Comment> comment) {
        List<GetCommentsResponse> commentsResponse = comment.stream().map(
                comm -> new GetCommentsResponse(
                        comm.getId(),
                        comm.getText(),
                        comm.getPostId()
                )
        ).toList();

        return new GetAllCommentsResponse(commentsResponse);
    }

    public GetCommentsResponse assembleGetCommentsResponse(Comment comment) {
        return new GetCommentsResponse(
                comment.getId(),
                comment.getText(),
                comment.getPostId()
        );
    }

    public AddCommentResponse assembleAddCommentsResponse(Comment comment) {
        return new AddCommentResponse(
                comment.getId(),
                comment.getText(),
                comment.getPostId()
        );
    }

    public UpdateCommentResponse assembleUpdateCommentResponse(Comment comment) {
        return new UpdateCommentResponse(
                comment.getId(),
                comment.getText(),
                comment.getPostId()
        );
    }

}
