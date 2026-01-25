package ru.blog.service.comment;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.blog.entity.comment.Comment;
import ru.blog.entity.comment.CommentsRepository;
import ru.blog.service.comment.dto.getComments.GetCommentsRequest;
import ru.blog.service.comment.dto.getComments.GetCommentsResponse;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentsRepository commentsRepository;
    private final CommentAssembler commentAssembler;

    public ResponseEntity<?> invoke(GetCommentsRequest request) {
        List<Comment> comments = commentsRepository.findAllByPostId(request.getPostId());

        if (comments.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        GetCommentsResponse responses = commentAssembler.assembleGetCommentsResponse(comments);

        return new ResponseEntity<>(responses, HttpStatus.OK);
    }

}
