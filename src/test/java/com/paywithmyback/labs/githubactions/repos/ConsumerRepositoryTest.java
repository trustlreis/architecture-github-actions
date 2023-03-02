package com.paywithmyback.labs.githubactions.repos;

import static org.junit.jupiter.api.Assertions.assertNull;

import com.paywithmyback.labs.githubactions.data.Consumer;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = {ConsumerRepository.class})
@EnableAutoConfiguration
@EntityScan(basePackages = {"com.paywithmyback.labs.githubactions.data"})
@DataJpaTest
class ConsumerRepositoryTest {

    @Autowired
    private ConsumerRepository consumerRepository;

    /**
     * Method under test: {@link ConsumerRepository#findByName(String)}
     */
    @Test
    void testFindByName() {
        Consumer consumer = new Consumer();
        consumer.setId(1L);
        consumer.setName("Name");

        Consumer consumer1 = new Consumer();
        consumer1.setId(1L);
        consumer1.setName("Name");
        consumerRepository.save(consumer);
        consumerRepository.save(consumer1);
        assertNull(consumerRepository.findByName("foo"));
    }
}

