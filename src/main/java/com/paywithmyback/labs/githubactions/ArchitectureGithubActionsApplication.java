package com.paywithmyback.labs.githubactions;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.metrics.buffering.BufferingApplicationStartup;

@SpringBootApplication
public class ArchitectureGithubActionsApplication {

    private static final int PORT_APPLICATION_STARTUP = 9090;

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(ArchitectureGithubActionsApplication.class);
        app.setApplicationStartup(new BufferingApplicationStartup(PORT_APPLICATION_STARTUP));
        app.run(args);
    }

}
