package ru.blog.service.post;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.blog.entity.post.Post;
import ru.blog.entity.post.PostRepository;
import ru.blog.service.post.dto.addLike.AddPostLikeRequest;
import ru.blog.service.post.dto.addLike.AddPostLikeResponse;
import ru.blog.service.post.dto.createPost.CreatePostRequest;
import ru.blog.service.post.dto.createPost.CreatePostResponse;
import ru.blog.service.post.dto.delete.DeletePostRequest;
import ru.blog.service.post.dto.getPhoto.GetPostPhotoRequest;
import ru.blog.service.post.dto.getPost.GetPostRequest;
import ru.blog.service.post.dto.getPost.GetPostResponse;
import ru.blog.service.post.dto.search.SearchPostResponse;
import ru.blog.service.post.dto.search.SearchPostsRequest;
import ru.blog.service.post.dto.update.UpdatePostRequest;
import ru.blog.service.post.dto.update.UpdatePostResponse;
import ru.blog.service.post.dto.updatePhoto.UpdatePostPhotoRequest;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostResponseAssembler postResponseAssembler;
    private final PostRepository postRepository;

    public ResponseEntity<?> invoke(CreatePostRequest request) {
        Post createdPost = postRepository.save(new Post(
                null,
                request.getTitle(),
                request.getText(),
                request.getTags(),
                0,
                0,
                null,
                null,
                Collections.emptySet()
            )
        );
        CreatePostResponse response = postResponseAssembler.assembleCreatePostResponse(createdPost);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<?> invoke(SearchPostsRequest request) {
        String searchPattern = "%" + request.getSearch() + "%";
        List<Post> posts = postRepository.findByContentWithPagination(searchPattern, request.getPageSize(), request.getOffset());
        Integer postsCount = postRepository.countPostsByContent(searchPattern);
        SearchPostResponse response = postResponseAssembler.assembleSearchPostsResponse(posts, request.getPageNumber(), request.getPageSize(), postsCount);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<?> invoke(GetPostRequest request) {
        Optional<Post> post = postRepository.findById(request.getId());

        return post.map(p -> {
                GetPostResponse response = postResponseAssembler.assembleGetPostsResponse(p);
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
        ).orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<?> invoke(UpdatePostRequest request, Long id) {
        Optional<Post> post = postRepository.findById(id);

        return post.map(p -> {
                Post newPost = new Post(
                        p.getId(),
                        request.getTitle(),
                        request.getText(),
                        request.getTags(),
                        p.getLikesCount(),
                        p.getCommentsCount(),
                        p.getImageName(),
                        p.getImageData(),
                        p.getComments()
                );
                Post updPost = postRepository.save(newPost);
                UpdatePostResponse response = postResponseAssembler.assembleUpdatePostResponse(updPost);
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
        ).orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<?> invoke(DeletePostRequest request) {
        postRepository.deleteById(request.getId());
        return ResponseEntity.ok().build();
    }


    public ResponseEntity<?> invoke(AddPostLikeRequest request) {
        Optional<Post> post = postRepository.findById(request.getId());

        return post.map(p -> {
                    postRepository.addPostLike(p.getId());
                    AddPostLikeResponse response = postResponseAssembler.assembleAddPostLikeResponse(p.getLikesCount() + 1);
                    return new ResponseEntity<>(response, HttpStatus.OK);
                }
        ).orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<?> invoke(UpdatePostPhotoRequest request) {
        Optional<Post> post = postRepository.findById(request.getPostId());

        return post.map(p -> {
                    p.setImageName(request.getFile().getOriginalFilename());
                    try {
                        p.setImageData(request.getFile().getBytes());
                    } catch (IOException e) {
                        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                                "Error updating photo: " + Arrays.toString(e.getStackTrace())
                        );
                    }
                    return ResponseEntity.ok().build();
                }
        ).orElse(ResponseEntity.notFound().build());
    }


    public ResponseEntity<?> invoke(GetPostPhotoRequest request) {
        Optional<Post> post = postRepository.findById(request.getPostId());

        return post.map(p -> {
                    if (p.getImageData() == null) return ResponseEntity.notFound().build();
                    return new ResponseEntity<>(p.getImageData(), HttpStatus.OK);
                }
        ).orElse(ResponseEntity.notFound().build());
    }

}
