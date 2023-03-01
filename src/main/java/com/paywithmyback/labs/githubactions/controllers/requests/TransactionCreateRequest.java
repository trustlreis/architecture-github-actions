package com.paywithmyback.labs.githubactions.controllers.requests;

public record TransactionCreateRequest(Long merchantId, Long consumerId, Double amount) {

}
