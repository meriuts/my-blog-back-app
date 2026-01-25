package ru.blog.service.post;

import org.springframework.stereotype.Component;
import ru.blog.entity.post.Post;
import ru.blog.service.post.dto.addLike.AddPostLikeResponse;
import ru.blog.service.post.dto.createPost.CreatePostResponse;
import ru.blog.service.post.dto.getPost.GetPostResponse;
import ru.blog.service.post.dto.search.SearchPostResponse;
import ru.blog.service.post.dto.update.UpdatePostResponse;

import java.util.List;

@Component
public class PostAssembler {

    public CreatePostResponse assembleCreatePostResponse(Post post) {
        return new CreatePostResponse(
                post.getTitle(),
                post.getText(),
                post.getTags(),
                post.getLikesCount(),
                post.getCommentsCount()
        );
    }

    public UpdatePostResponse assembleUpdatePostResponse(Post post) {
        return new UpdatePostResponse(
                post.getTitle(),
                post.getText(),
                post.getTags(),
                post.getLikesCount(),
                post.getCommentsCount()
        );
    }

    public SearchPostResponse assembleSearchPostsResponse(List<Post> posts, Integer pageNumber, Integer pageSize, Integer postsCount) {
       return new SearchPostResponse(
                posts,
                pageNumber > 0,
                pageNumber < postsCount,
                postsCount / pageSize
        );
    }

    public GetPostResponse assembleGetPostsResponse(Post post) {
        return new GetPostResponse(
                post.getId(),
                post.getTitle(),
                post.getText(),
                post.getTags(),
                post.getLikesCount(),
                post.getCommentsCount()
        );
    }

    public AddPostLikeResponse assembleAddPostLikeResponse(Integer likeCount) {
        return new AddPostLikeResponse(likeCount);
    }
}
