package com.paywithmyback.labs.githubactions.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Transaction {

    private String uid;
    private Merchant merchant;
    private Consumer consumer;
    private Double amount;

}
