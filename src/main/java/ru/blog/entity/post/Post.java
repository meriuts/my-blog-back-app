package ru.blog.entity.post;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;
import ru.blog.entity.comment.Comment;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table("posts")
public class Post {

    @Id
    private Long id;

    private String title;
    private String text;
    private List<String> tags;
    @Column("likes_count")
    private Integer likesCount;
    @Column("comments_count")
    private Integer commentsCount;
    @Column("image_name")
    private String imageName;
    @Column("image_data")
    private byte[] imageData;
    @MappedCollection(idColumn = "post_id")
    private Set<Comment> comments;

}
