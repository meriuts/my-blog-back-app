package ru.blog.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest
@Testcontainers
public class ServiceTest {

    public static ObjectMapper mapper = new ObjectMapper();

}
