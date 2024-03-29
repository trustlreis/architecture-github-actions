package com.paywithmyback.labs.githubactions.services;

import com.paywithmyback.labs.githubactions.data.Consumer;
import com.paywithmyback.labs.githubactions.data.Merchant;
import com.paywithmyback.labs.githubactions.data.Transaction;
import com.paywithmyback.labs.githubactions.repos.ConsumerRepository;
import com.paywithmyback.labs.githubactions.repos.MerchantRepository;
import com.sun.istack.NotNull;

import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class TransactionManagerService {

    private final MerchantRepository merchantRepository;

    private final ConsumerRepository consumerRepository;

    public Transaction createFakeTransaction(@NotNull final Long merchantId, @NotNull final Long consumerId, @NotNull final Double amount) {
        Optional<Merchant> merchant = merchantRepository.findById(merchantId);
        Optional<Consumer> consumer = consumerRepository.findById(consumerId);

        log.info("adding some log");
        return new Transaction(UUID.randomUUID().toString(), merchant.orElseThrow(NoSuchElementException::new), consumer.orElseThrow(NoSuchElementException::new), amount);
    }

}
