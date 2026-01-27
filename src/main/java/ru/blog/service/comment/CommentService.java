package ru.blog.service.comment;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.blog.entity.comment.Comment;
import ru.blog.entity.comment.CommentsRepository;
import ru.blog.entity.post.Post;
import ru.blog.entity.post.PostRepository;
import ru.blog.service.comment.dto.addComment.AddCommentRequest;
import ru.blog.service.comment.dto.addComment.AddCommentResponse;
import ru.blog.service.comment.dto.deleteComent.DeleteCommentRequest;
import ru.blog.service.comment.dto.getAllComments.GetAllCommentsRequest;
import ru.blog.service.comment.dto.getAllComments.GetAllCommentsResponse;
import ru.blog.service.comment.dto.getComment.GetCommentRequest;
import ru.blog.service.comment.dto.updateComment.UpdateCommentRequest;
import ru.blog.service.comment.dto.updateComment.UpdateCommentResponse;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentsRepository commentsRepository;
    private PostRepository postRepository;
    private final CommentAssembler commentAssembler;

    public ResponseEntity<?> invoke(GetAllCommentsRequest request) {
        List<Comment> comments = commentsRepository.findAllByPostId(request.getPostId());

        if (comments.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        GetAllCommentsResponse responses = commentAssembler.assembleGetAllCommentsResponse(comments);

        return new ResponseEntity<>(responses, HttpStatus.OK);
    }

    public ResponseEntity<?> invoke(GetCommentRequest request) {
        Optional<Post> post = postRepository.findById(request.getPostId());

        return post
                .flatMap(p -> p.getComments().stream()
                        .filter(comment -> comment.getId().equals(request.getCommentId()))
                        .findFirst()
                        .map(commentAssembler::assembleGetCommentsResponse)
                        .map(response -> new ResponseEntity<>(response, HttpStatus.OK))
                )
                .orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<?> invoke(AddCommentRequest request, Long postId) {
        Optional<Post> post = postRepository.findById(postId);

        return post.map(p -> {
                Comment comment = new Comment(null, request.getText(), request.getPostId());
                Comment newComment = commentsRepository.save(comment);
                AddCommentResponse response = commentAssembler.assembleAddCommentsResponse(newComment);
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
        ).orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<?> invoke(UpdateCommentRequest request, Long postId, Long commentId) {
        Optional<Post> post = postRepository.findById(postId);

        return post
                .flatMap(p -> p.getComments().stream()
                        .filter(comment -> comment.getId().equals(commentId))
                        .findFirst()
                        .map(comment -> {
                            comment.setText(request.getText());
                            return commentsRepository.save(comment);
                        })
                        .map(savedComment -> {
                            UpdateCommentResponse response = commentAssembler.assembleUpdateCommentResponse(savedComment);
                            return new ResponseEntity<>(response, HttpStatus.OK);
                        })
                )
                .orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<?> invoke(DeleteCommentRequest request) {
        Optional<Post> post = postRepository.findById(request.getPostId());

        return post
                .flatMap(p -> p.getComments().stream()
                        .filter(comment -> comment.getId().equals(request.getCommentId()))
                        .findFirst()
                        .map(comment -> {
                            commentsRepository.deleteById(comment.getId());
                            return ResponseEntity.ok().build();
                        }
                    )
                )
                .orElse(ResponseEntity.notFound().build());
    }

}
