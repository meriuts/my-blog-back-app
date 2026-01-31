//package ru.blog.service;
//
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import ru.blog.TestConfig;
//import ru.blog.config.AppConfig;
//import ru.blog.entity.post.PostRepository;
//import ru.blog.service.post.PostService;
//
//@ExtendWith(SpringExtension.class)
//@ContextConfiguration(classes = {
//        AppConfig.class,
//        TestConfig.class
//})
//class PostServiceTest {
//
//    @Autowired
//    private PostService postService;
//
//    @Autowired
//    private PostRepository postRepository;
//
//    @Test
//    void invoke_shouldSavePost() {
////        // given
////        CreatePostRequest request = new CreatePostRequest("title", "text", List.of("java"));
////
////        Post savedPost = new Post(
////                1L, "title", "text", List.of("java"),
////                0, 0, null, null, Collections.emptyList()
////        );
////
////        Mockito.when(postRepository.save(Mockito.any(Post.class))).thenReturn(savedPost);
////
////        ResponseEntity<?> response = postService.invoke(request);
////
////        assertEquals(HttpStatus.OK, response.getStatusCode());
////        Mockito.verify(postRepository).save(Mockito.any(Post.class));
//    }
//}
