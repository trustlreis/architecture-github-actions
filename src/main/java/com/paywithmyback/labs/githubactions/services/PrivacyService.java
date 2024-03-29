package com.paywithmyback.labs.githubactions.services;

import com.paywithmyback.labs.githubactions.data.Consumer;
import com.paywithmyback.labs.githubactions.repos.ConsumerRepository;
import com.sun.istack.NotNull;

import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@AllArgsConstructor
public class PrivacyService {

    private final ConsumerRepository consumerRepository;

    public void dropAllData(final Consumer consumer, final String reason) {
        Objects.nonNull(consumer);
        Objects.nonNull(reason);

        log.info("Deleting all data for '{}' because '{}'", consumer.getName(), reason);
    }

    public void dropAllData(final Long consumerId, final String reason) {
        Optional<Consumer> consumer = consumerRepository.findById(consumerId);
        dropAllData(consumer.orElseThrow(NoSuchElementException::new), reason);
    }
}
