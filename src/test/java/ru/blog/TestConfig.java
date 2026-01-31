package ru.blog;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import ru.blog.entity.post.PostRepository;

@Configuration
public class TestConfig {

    @Bean
    @Primary
    public PostRepository postRepository() {
        return Mockito.mock(PostRepository.class);
    }

}
