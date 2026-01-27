package ru.blog.entity.post;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;
import ru.blog.entity.comment.Comment;

import java.util.List;

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
    private String[] tags;
    private Integer likesCount;
    private Integer commentsCount;
    private String imageName;
    private byte[] imageData;
    @MappedCollection(idColumn = "postId")
    private List<Comment> comments;

}
