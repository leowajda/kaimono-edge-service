package com.kaimono.edge.service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class KaimonoEdgeServiceApplicationTests {

    private static final int REDIS_PORT = 6379;

    @Container
    private static GenericContainer<?> redis =
            new GenericContainer<>(DockerImageName.parse("redis:7.0"))
                    .withExposedPorts(REDIS_PORT);

    @DynamicPropertySource
    private static void redisProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.redis.host", redis::getHost);
        registry.add("spring.redis.port", () -> redis.getMappedPort(REDIS_PORT));
    }

    @Test
    public void verifyThatSpringContextLoads() {}

}
