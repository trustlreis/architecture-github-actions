package com.paywithmyback.labs.githubactions.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.paywithmyback.labs.githubactions.controllers.requests.PrivacyDropAllDataRequest;
import com.paywithmyback.labs.githubactions.services.PrivacyService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {PrivacyController.class})
@ExtendWith(SpringExtension.class)
class PrivacyControllerTest {

    @Autowired
    private PrivacyController privacyController;

    @MockBean
    private PrivacyService privacyService;

    /**
     * Method under test: {@link PrivacyController#dropAllData(PrivacyDropAllDataRequest)}
     */
    @Test
    void testDropAllData() throws Exception {
        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders.get("/consumer/privacy")
                .contentType(MediaType.APPLICATION_JSON);

        ObjectMapper objectMapper = new ObjectMapper();
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult.content(
                objectMapper.writeValueAsString(new PrivacyDropAllDataRequest(1L, "Just cause")));
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(privacyController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status()
                                              .is(405));
    }
}

