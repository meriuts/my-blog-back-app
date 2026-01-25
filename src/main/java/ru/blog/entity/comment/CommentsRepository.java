package ru.blog.entity.comment;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentsRepository extends CrudRepository<Comment, Long> {

    List<Comment> findAllByPostId(Long postId);
}
