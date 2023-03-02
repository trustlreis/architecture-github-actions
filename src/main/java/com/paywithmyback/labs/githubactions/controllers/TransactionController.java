package com.paywithmyback.labs.githubactions.controllers;

import com.paywithmyback.labs.githubactions.controllers.requests.TransactionCreateRequest;
import com.paywithmyback.labs.githubactions.data.Transaction;
import com.paywithmyback.labs.githubactions.services.TransactionManagerService;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping(TransactionController.ENDPOINT_PREFIX)
public class TransactionController {

    public static final String ENDPOINT_PREFIX = "/tx";

    private TransactionManagerService transactionManagerService;

    @PutMapping
    public Transaction createTransaction(@RequestBody TransactionCreateRequest request) {
        Objects.requireNonNull(request, "request cannot be null");
        return transactionManagerService.createFakeTransaction(request.merchantId(), request.consumerId(), request.amount());
    }
}
