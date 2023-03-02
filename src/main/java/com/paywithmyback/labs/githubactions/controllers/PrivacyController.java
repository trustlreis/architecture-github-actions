package com.paywithmyback.labs.githubactions.controllers;

import com.paywithmyback.labs.githubactions.controllers.requests.PrivacyDropAllDataRequest;
import com.paywithmyback.labs.githubactions.services.PrivacyService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping(PrivacyController.ENDPOINT_PREFIX)
public class PrivacyController {

    public static final String ENDPOINT_PREFIX = "/consumer/privacy";

    private PrivacyService privacyService;

    @DeleteMapping
    public void dropAllData(@RequestBody PrivacyDropAllDataRequest request) {
        Objects.requireNonNull(request, "request cannot be null");
        privacyService.dropAllData(request.consumerId(), request.reason());
    }
}
