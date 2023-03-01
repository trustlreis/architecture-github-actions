package com.paywithmyback.labs.githubactions.data;

public record Transaction(String uid, Merchant merchant, Consumer consumer, Double amount) {

}
