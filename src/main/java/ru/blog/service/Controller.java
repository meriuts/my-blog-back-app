package ru.blog.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.blog.service.comment.CommentService;
import ru.blog.service.comment.dto.addComment.AddCommentRequest;
import ru.blog.service.comment.dto.deleteComent.DeleteCommentRequest;
import ru.blog.service.comment.dto.getAllComments.GetAllCommentsRequest;
import ru.blog.service.comment.dto.getComment.GetCommentRequest;
import ru.blog.service.comment.dto.updateComment.UpdateCommentRequest;
import ru.blog.service.post.PostService;
import ru.blog.service.post.dto.addLike.AddPostLikeRequest;
import ru.blog.service.post.dto.createPost.CreatePostRequest;
import ru.blog.service.post.dto.delete.DeletePostRequest;
import ru.blog.service.post.dto.getPhoto.GetPostPhotoRequest;
import ru.blog.service.post.dto.getPost.GetPostRequest;
import ru.blog.service.post.dto.search.SearchPostsRequest;
import ru.blog.service.post.dto.update.UpdatePostRequest;
import ru.blog.service.post.dto.updatePhoto.UpdatePostPhotoRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class Controller {

    private final PostService postService;
    private final CommentService commentService;

    @PostMapping("/posts")
    public ResponseEntity<?> createPost(@RequestBody CreatePostRequest request) {
        return postService.invoke(request);
    }

    @GetMapping("/posts")
    public ResponseEntity<?> getPosts(
            @RequestParam("search") String search,
            @RequestParam("pageNumber") Integer pageNumber,
            @RequestParam("pageSize") Integer pageSize
    ) {
        return postService.invoke(new SearchPostsRequest(search, pageNumber, pageSize, pageNumber * pageSize));
    }

    @PostMapping("/posts/{id}")
    public ResponseEntity<?> getPost(@PathVariable("id") Long id) {
        return postService.invoke(new GetPostRequest(id));
    }

    @PutMapping("/posts/{id}")
    public ResponseEntity<?> updatePost(
            @RequestBody UpdatePostRequest request,
            @PathVariable("id") Long id) {
        return postService.invoke(request, id);
    }

    @DeleteMapping("/posts/{id}")
    public ResponseEntity<?> deletePost(@PathVariable("id") Long id) {
        return postService.invoke(new DeletePostRequest(id));
    }

    @PostMapping("/posts/{id}/likes")
    public ResponseEntity<?> addLike(@PathVariable("id") Long id) {
        return postService.invoke(new AddPostLikeRequest(id));
    }

    @PutMapping(value = "/posts/{id}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> updatePhoto(
            @PathVariable("id") Long id,
            @RequestParam("image") MultipartFile image
    ) {
        return postService.invoke(new UpdatePostPhotoRequest(id, image));
    }

    @GetMapping("/posts/{postId}/image")
    public ResponseEntity<?> getPostPhoto(@PathVariable("postId") Long postId) {
        return postService.invoke(new GetPostPhotoRequest(postId));
    }

    @GetMapping("/posts/{postId}/comments")
    public ResponseEntity<?> getComments(@PathVariable("postId") Long postId) {
        return commentService.invoke(new GetAllCommentsRequest(postId));
    }

    @GetMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<?> getComment(
            @PathVariable("postId") Long postId,
            @PathVariable("commentId") Long commentId
        ) {
        return commentService.invoke(new GetCommentRequest(postId, commentId));
    }

    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<?> addComment(
            @RequestBody AddCommentRequest request,
            @PathVariable("postId") Long postId
    ) {
        return commentService.invoke(request, postId);
    }

    @PutMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<?> updateCommentPost(
            @RequestBody UpdateCommentRequest request,
            @PathVariable("postId") Long postId,
            @PathVariable("commentId") Long commentId
    ) {
        return commentService.invoke(request, postId, commentId);
    }

    @DeleteMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<?> deleteComment(
            @PathVariable("postId") Long postId,
            @PathVariable("commentId") Long commentId
    ) {
        return commentService.invoke(new DeleteCommentRequest(postId,commentId));
    }

}
