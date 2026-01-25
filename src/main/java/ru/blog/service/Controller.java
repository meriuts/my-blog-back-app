package ru.blog.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.blog.service.comment.CommentService;
import ru.blog.service.comment.dto.getComments.GetCommentsRequest;
import ru.blog.service.post.PostService;
import ru.blog.service.post.dto.addLike.AddPostLikeRequest;
import ru.blog.service.post.dto.createPost.CreatePostRequest;
import ru.blog.service.post.dto.delete.DeletePostRequest;
import ru.blog.service.post.dto.getPost.GetPostRequest;
import ru.blog.service.post.dto.search.SearchPostsRequest;
import ru.blog.service.post.dto.update.UpdatePostRequest;

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
    public ResponseEntity<?> getPost(@PathVariable Long id) {
        return postService.invoke(new GetPostRequest(id));
    }

    @PutMapping("/posts/{id}")
    public ResponseEntity<?> updatePost(
            @RequestBody UpdatePostRequest request,
            @PathVariable Long id) {
        return postService.invoke(request, id);
    }

    @DeleteMapping("/posts/{id}")
    public ResponseEntity<?> deletePost(@PathVariable Long id) {
        return postService.invoke(new DeletePostRequest(id));
    }

    @PostMapping("/posts/{id}/likes")
    public ResponseEntity<?> addLike(@PathVariable Long id) {
        return postService.invoke(new AddPostLikeRequest(id));
    }

    @GetMapping("/posts/{id}/comments")
    public ResponseEntity<?> getComments(@PathVariable Long id) {
        return commentService.invoke(new GetCommentsRequest(id));
    }

}
