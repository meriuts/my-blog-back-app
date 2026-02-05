package ru.blog.entity.post;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends CrudRepository<Post, Long> {

    @Query("SELECT * FROM posts WHERE title LIKE :search OR text LIKE :search  LIMIT :pageSize OFFSET :offset")
    List<Post> findByContentWithPagination(
            @Param("search") String search,
            @Param("pageSize") Integer pageSize,
            @Param("offset") Integer offset
    );

    @Query("SELECT COUNT(*) FROM posts WHERE title LIKE :search OR text LIKE :search")
    Integer countPostsByContent(@Param("search") String search);

    @Modifying
    @Query("UPDATE posts SET likes_count = likes_count + 1 WHERE id = :postId")
    void addPostLike(@Param("postId") Long postId);

}
