package com.paywithmyback.labs.githubactions.controllers;

import com.paywithmyback.labs.githubactions.controllers.requests.TransactionCreateRequest;
import com.paywithmyback.labs.githubactions.data.Transaction;
import com.paywithmyback.labs.githubactions.services.TransactionManagerService;
import com.sun.istack.NotNull;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping(TransactionController.ENDPOINT_PREFIX)
public class TransactionController {

    public static final String ENDPOINT_PREFIX = "/tx";

    private TransactionManagerService transactionManagerService;

    @PutMapping
    public Transaction createTransaction(@NotNull @RequestBody TransactionCreateRequest request) {
        Transaction tx = transactionManagerService.createFakeTransaction(request.merchantId(), request.consumerId(), request.amount());
        return tx;
    }
}
