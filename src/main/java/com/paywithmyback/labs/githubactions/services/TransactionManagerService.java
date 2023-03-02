package com.paywithmyback.labs.githubactions.services;

import com.paywithmyback.labs.githubactions.data.Consumer;
import com.paywithmyback.labs.githubactions.data.Merchant;
import com.paywithmyback.labs.githubactions.data.Transaction;
import com.paywithmyback.labs.githubactions.repos.ConsumerRepository;
import com.paywithmyback.labs.githubactions.repos.MerchantRepository;

import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Objects;
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

    public Transaction createFakeTransaction(final Long merchantId, final Long consumerId, final Double amount) {
        Objects.requireNonNull(merchantId, "merchantId cannot be null");
        Objects.requireNonNull(consumerId, "consumerId cannot be null");
        Objects.requireNonNull(amount, "amount cannot be null");

        Optional<Merchant> merchant = merchantRepository.findById(merchantId);
        Optional<Consumer> consumer = consumerRepository.findById(consumerId);

        log.info("adding some log");
        return new Transaction(UUID.randomUUID().toString(), merchant.orElseThrow(NoSuchElementException::new), consumer.orElseThrow(NoSuchElementException::new), amount);
    }

}
