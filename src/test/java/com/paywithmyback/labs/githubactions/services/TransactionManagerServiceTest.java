package com.paywithmyback.labs.githubactions.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.paywithmyback.labs.githubactions.data.Consumer;
import com.paywithmyback.labs.githubactions.data.Merchant;
import com.paywithmyback.labs.githubactions.data.Transaction;
import com.paywithmyback.labs.githubactions.repos.ConsumerRepository;
import com.paywithmyback.labs.githubactions.repos.MerchantRepository;

import java.util.NoSuchElementException;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {TransactionManagerService.class})
@ExtendWith(SpringExtension.class)
class TransactionManagerServiceTest {

    @MockBean
    private ConsumerRepository consumerRepository;

    @MockBean
    private MerchantRepository merchantRepository;

    @Autowired
    private TransactionManagerService transactionManagerService;

    /**
     * Method under test: {@link TransactionManagerService#createFakeTransaction(Long, Long, Double)}
     */
    @Test
    void testCreateFakeTransaction() {
        Merchant merchant = new Merchant();
        merchant.setId(1L);
        merchant.setName("Name");
        Optional<Merchant> ofResult = Optional.of(merchant);
        when(merchantRepository.findById((Long) any())).thenReturn(ofResult);

        Consumer consumer = new Consumer();
        consumer.setId(1L);
        consumer.setName("Name");
        Optional<Consumer> ofResult1 = Optional.of(consumer);
        when(consumerRepository.findById((Long) any())).thenReturn(ofResult1);
        Transaction actualCreateFakeTransactionResult = transactionManagerService.createFakeTransaction(1L, 1L, 10.0d);
        assertEquals(10.0d, actualCreateFakeTransactionResult.getAmount()
                .doubleValue());
        assertSame(merchant, actualCreateFakeTransactionResult.getMerchant());
        assertSame(consumer, actualCreateFakeTransactionResult.getConsumer());
        verify(merchantRepository).findById((Long) any());
        verify(consumerRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link TransactionManagerService#createFakeTransaction(Long, Long, Double)}
     */
    @Test
    void testCreateFakeTransaction2() {
        Merchant merchant = new Merchant();
        merchant.setId(1L);
        merchant.setName("Name");
        Optional<Merchant> ofResult = Optional.of(merchant);
        when(merchantRepository.findById((Long) any())).thenReturn(ofResult);
        when(consumerRepository.findById((Long) any())).thenThrow(new NoSuchElementException());
        assertThrows(NoSuchElementException.class, () -> transactionManagerService.createFakeTransaction(1L, 1L, 10.0d));
        verify(merchantRepository).findById((Long) any());
        verify(consumerRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link TransactionManagerService#createFakeTransaction(Long, Long, Double)}
     */
    @Test
    void testCreateFakeTransaction3() {
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            when(merchantRepository.findById((Long) any())).thenReturn(Optional.empty());

            Consumer consumer = new Consumer();
            consumer.setId(1L);
            consumer.setName("Name");
            Optional<Consumer> ofResult = Optional.of(consumer);
            when(consumerRepository.findById((Long) any())).thenReturn(ofResult);
            transactionManagerService.createFakeTransaction(1L, 1L, 10.0d);
        });
    }

    /**
     * Method under test: {@link TransactionManagerService#createFakeTransaction(Long, Long, Double)}
     */
    @Test
    void testCreateFakeTransaction4() {
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            Merchant merchant = new Merchant();
            merchant.setId(1L);
            merchant.setName("Name");
            Optional<Merchant> ofResult = Optional.of(merchant);
            when(merchantRepository.findById((Long) any())).thenReturn(ofResult);
            when(consumerRepository.findById((Long) any())).thenReturn(Optional.empty());
            transactionManagerService.createFakeTransaction(1L, 1L, 10.0d);
        });
    }
}

