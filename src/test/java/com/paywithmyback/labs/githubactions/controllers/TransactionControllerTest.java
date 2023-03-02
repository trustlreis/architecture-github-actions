package com.paywithmyback.labs.githubactions.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.paywithmyback.labs.githubactions.controllers.requests.TransactionCreateRequest;
import com.paywithmyback.labs.githubactions.services.TransactionManagerService;

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

@ContextConfiguration(classes = {TransactionController.class})
@ExtendWith(SpringExtension.class)
class TransactionControllerTest {

    @Autowired
    private TransactionController transactionController;

    @MockBean
    private TransactionManagerService transactionManagerService;

    /**
     * Method under test: {@link TransactionController#createTransaction(TransactionCreateRequest)}
     */
    @Test
    void testCreateTransaction() throws Exception {
        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders.get("/tx")
                .contentType(MediaType.APPLICATION_JSON);

        ObjectMapper objectMapper = new ObjectMapper();
        MockHttpServletRequestBuilder requestBuilder =
                contentTypeResult.content(objectMapper.writeValueAsString(new TransactionCreateRequest(1L, 1L, 10.0d)));
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(transactionController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status()
                                              .is(405));
    }
}

