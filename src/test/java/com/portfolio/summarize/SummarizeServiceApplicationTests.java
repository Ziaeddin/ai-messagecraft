package com.portfolio.summarize;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(properties = {
    "openai.api-key=test-key",
    "openai.base-url=https://api.openai.com/v1",
    "openai.model=gpt-4",
    "openai.max-tokens=1000",
    "openai.temperature=0.7"
})
class SummarizeServiceApplicationTests {

    @Test
    void contextLoads() {
        // Test that the Spring context loads successfully
    }
} 