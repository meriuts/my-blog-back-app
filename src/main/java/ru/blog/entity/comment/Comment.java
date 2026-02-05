package ru.blog.entity.comment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table("comments")
public class Comment {

    @Id
    private Long id;

    private String text;

    @Column("post_id")
    private Long postId;

    @Column("created_at")
    private LocalDateTime createdAt;
}


