package ru.blog.service;

import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import ru.blog.service.comment.dto.addComment.AddCommentRequest;
import ru.blog.service.post.dto.createPost.CreatePostRequest;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class CommentServiceTest extends ServiceTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Container
    public static PostgreSQLContainer<?> postgresContainer = new PostgreSQLContainer<>("postgres:16.1")
            .withDatabaseName("blog")
            .withUsername("user")
            .withPassword("pass");

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    void shouldSaveComment() throws Exception {
        CreatePostRequest createPostRequest = new CreatePostRequest("shouldSaveComment", "Новый пост", List.of("java"));

        mockMvc.perform(post("/api/posts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(createPostRequest))
        );

        MvcResult post = mockMvc.perform(get("/api/posts")
                .param("search", "shouldSaveComment")
                .param("pageNumber", "0")
                .param("pageSize", "10")
                .contentType(MediaType.APPLICATION_JSON)
        ).andReturn();
        Number postId = JsonPath.read(post.getResponse().getContentAsString(), "$.posts[0].id");

        AddCommentRequest addCommentRequest = new AddCommentRequest("new comment", postId.longValue());


        mockMvc.perform(post("/api/posts/{postId}/comments", postId.longValue())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(addCommentRequest))
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.text").value("new comment"))
                .andExpect(jsonPath("$.postId").value(postId));
    }

    @Test
    void shouldGetCommentById() throws Exception {
        CreatePostRequest createPostRequest = new CreatePostRequest("shouldGetCommentById", "Новый пост", List.of("java"));

        mockMvc.perform(post("/api/posts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(createPostRequest))
        );

        MvcResult post = mockMvc.perform(get("/api/posts")
                .param("search", "shouldGetCommentById")
                .param("pageNumber", "0")
                .param("pageSize", "10")
                .contentType(MediaType.APPLICATION_JSON)
        ).andReturn();
        Number postId = JsonPath.read(post.getResponse().getContentAsString(), "$.posts[0].id");

        AddCommentRequest addCommentRequest = new AddCommentRequest("new comment", postId.longValue());

        MvcResult comment = mockMvc.perform(post("/api/posts/{postId}/comments", postId.longValue())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(addCommentRequest))
        ).andReturn();
        Number commentId = JsonPath.read(comment.getResponse().getContentAsString(), "$.id");

        mockMvc.perform(get("/api/posts/{postId}/comments/{commentId}", postId, commentId)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(commentId))
                .andExpect(jsonPath("$.text").value("new comment"))
                .andExpect(jsonPath("$.postId").value(postId));
    }
}
