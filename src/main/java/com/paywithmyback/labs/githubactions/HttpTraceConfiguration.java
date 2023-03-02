package com.paywithmyback.labs.githubactions;

import org.springframework.boot.actuate.trace.http.HttpTraceRepository;
import org.springframework.boot.actuate.trace.http.InMemoryHttpTraceRepository;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HttpTraceConfiguration {

    private static final String PREFIX_MANAGEMENT_ENDPOINT_TRACE = "management.endpoint.trace";
    private static final String TRACE_ENABLED = "enabled";

    @Bean
    @ConditionalOnProperty(prefix = PREFIX_MANAGEMENT_ENDPOINT_TRACE, name = TRACE_ENABLED, havingValue = "true")
    public HttpTraceRepository httpTraceRepository() {
        return new InMemoryHttpTraceRepository();
    }

}
