package ru.blog.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testcontainers.junit.jupiter.Testcontainers;
import ru.blog.config.DataSourceConfiguration;
import ru.blog.config.WebConfiguration;

@SpringJUnitConfig(classes = {
        DataSourceConfiguration.class,
        WebConfiguration.class,
})
@WebAppConfiguration
@Testcontainers
public class ServiceTest {

    public static ObjectMapper mapper = new ObjectMapper();

}
