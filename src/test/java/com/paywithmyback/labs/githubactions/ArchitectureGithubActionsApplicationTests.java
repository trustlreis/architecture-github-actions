package com.paywithmyback.labs.githubactions;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = ArchitectureGithubActionsApplication.class
)
@AutoConfigureMockMvc
class ArchitectureGithubActionsApplicationTests {

    @Test
    void contextLoads() {
    }

}
