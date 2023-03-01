package com.paywithmyback.labs.githubactions.services;

import com.paywithmyback.labs.githubactions.data.Consumer;
import com.paywithmyback.labs.githubactions.data.Merchant;
import com.paywithmyback.labs.githubactions.data.Transaction;
import com.paywithmyback.labs.githubactions.repos.ConsumerRepository;
import com.paywithmyback.labs.githubactions.repos.MerchantRepository;
import com.sun.istack.NotNull;

import org.springframework.stereotype.Service;

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

        if (!merchant.isPresent()) {
            throw new RuntimeException("Merchant ID %d does not exist!".formatted(merchantId));
        }

        Optional<Consumer> consumer = consumerRepository.findById(consumerId);

        if (!consumer.isPresent()) {
            throw new RuntimeException("Consumer ID %d does not exist!".formatted(consumerId));
        }

        log.info("adding some log");
        return new Transaction(UUID.randomUUID().toString(), merchant.get(), consumer.get(), amount);
    }

}
