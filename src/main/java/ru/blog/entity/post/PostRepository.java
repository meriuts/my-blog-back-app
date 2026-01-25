package ru.blog.entity.post;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends CrudRepository<Post, Long> {

    @Query("SELECT * FROM posts WHERE title LIKE %:search% OR text LIKE %:search%  LIMIT :pageSize OFFSET :offset")
    List<Post> findByContentWithPagination(String search, Integer pageSize, Integer offset);

    @Query("SELECT COUNT(*) FROM posts WHERE title LIKE %:search% OR text LIKE %:search%")
    Integer countPostsByContent(String search);

    @Modifying
    @Query("UPDATE Post p SET p.likesCount = p.likesCount + 1 WHERE p.id = :postId")
    void addPostLike(Long postId);

}
