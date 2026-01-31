package ru.blog.service.post.dto.updatePhoto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePostPhotoRequest {
    private Long postId;
    private MultipartFile file;
}
