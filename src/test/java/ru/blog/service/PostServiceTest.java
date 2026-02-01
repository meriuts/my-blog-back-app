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
import ru.blog.service.post.dto.createPost.CreatePostRequest;
import ru.blog.service.post.dto.update.UpdatePostRequest;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class PostServiceTest extends ServiceTest{

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
    void shouldSavePost() throws Exception {
        CreatePostRequest request = new CreatePostRequest("Пост", "Новый пост", List.of("java"));

        mockMvc.perform(post("/api/posts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(request))
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.title").value("Пост"))
                .andExpect(jsonPath("$.text").value("Новый пост"));

    }

    @Test
    void shouldGetPosts() throws Exception {
        CreatePostRequest request = new CreatePostRequest("Пост", "Новый пост", List.of("java"));

        mockMvc.perform(post("/api/posts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(request))
        );

        mockMvc.perform(get("/api/posts")
                        .param("search", "Пост")
                        .param("pageNumber", "0")
                        .param("pageSize", "10")
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.posts").isArray())
                .andExpect(jsonPath("$.posts[0].title").value("Пост"))
                .andExpect(jsonPath("$.posts[0].text").value("Новый пост"))
                .andExpect(jsonPath("$.hasPrev").value(false))
                .andExpect(jsonPath("$.hasNext").value(true));
    }

    @Test
    void shouldGetPostById() throws Exception {
        CreatePostRequest request = new CreatePostRequest("shouldGetPostById", "Новый пост", List.of("java"));

        mockMvc.perform(post("/api/posts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(request))
        );

        MvcResult post = mockMvc.perform(get("/api/posts")
                .param("search", "shouldGetPostById")
                .param("pageNumber", "0")
                .param("pageSize", "10")
                .contentType(MediaType.APPLICATION_JSON)
        ).andReturn();
        Number postId = JsonPath.read(post.getResponse().getContentAsString(), "$.posts[0].id");

        mockMvc.perform(post("/api/posts/{id}", postId.longValue())
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))

                .andExpect(jsonPath("$.id").value(postId))
                .andExpect(jsonPath("$.title").value("shouldGetPostById"))
                .andExpect(jsonPath("$.text").value("Новый пост"))
                .andExpect(jsonPath("$.tags").isArray())
                .andExpect(jsonPath("$.tags[0]").value("java"))
                .andExpect(jsonPath("$.likesCount").value(0))
                .andExpect(jsonPath("$.commentsCount").value(0));
    }

    @Test
    void shouldUpdatePost() throws Exception {
        CreatePostRequest createPostRequest = new CreatePostRequest("shouldUpdatePost", "Новый пост", List.of("java"));

        mockMvc.perform(post("/api/posts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(createPostRequest))
        );

        MvcResult post = mockMvc.perform(get("/api/posts")
                .param("search", "shouldUpdatePost")
                .param("pageNumber", "0")
                .param("pageSize", "10")
                .contentType(MediaType.APPLICATION_JSON)
        ).andReturn();
        Number postId = JsonPath.read(post.getResponse().getContentAsString(), "$.posts[0].id");

        UpdatePostRequest request = new UpdatePostRequest(postId.longValue(),"Обновлённый пост", "Обновлённый текст", List.of("spring", "java"));
        mockMvc.perform(put("/api/posts/{id}", postId.longValue())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(request))
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.title").value("Обновлённый пост"))
                .andExpect(jsonPath("$.text").value("Обновлённый текст"))
                .andExpect(jsonPath("$.tags").isArray())
                .andExpect(jsonPath("$.tags[0]").value("spring"))
                .andExpect(jsonPath("$.tags[1]").value("java"));
    }

    @Test
    void shouldDeletePost() throws Exception {
        CreatePostRequest createPostRequest = new CreatePostRequest("shouldDeletePost", "Новый пост", List.of("java"));

        mockMvc.perform(post("/api/posts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(createPostRequest))
        );

        MvcResult post = mockMvc.perform(get("/api/posts")
                .param("search", "shouldDeletePost")
                .param("pageNumber", "0")
                .param("pageSize", "10")
                .contentType(MediaType.APPLICATION_JSON)
        ).andReturn();
        Number postId = JsonPath.read(post.getResponse().getContentAsString(), "$.posts[0].id");

        mockMvc.perform(delete("/api/posts/{id}", postId.longValue()))
                .andExpect(status().isOk());
    }

}