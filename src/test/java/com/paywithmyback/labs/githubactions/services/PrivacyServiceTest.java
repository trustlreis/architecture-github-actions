package com.paywithmyback.labs.githubactions.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.paywithmyback.labs.githubactions.data.Consumer;
import com.paywithmyback.labs.githubactions.repos.ConsumerRepository;

import java.util.NoSuchElementException;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {PrivacyService.class})
@ExtendWith(SpringExtension.class)
class PrivacyServiceTest {

    @MockBean
    private ConsumerRepository consumerRepository;

    @Autowired
    private PrivacyService privacyService;

    /**
     * Method under test: {@link PrivacyService#dropAllData(Consumer, String)}
     */
    @Test
    void testDropAllData() {
        Consumer consumer = new Consumer();
        consumer.setId(1L);
        consumer.setName("Name");
        privacyService.dropAllData(consumer, "Just cause");
        assertEquals(1L, consumer.getId()
                .longValue());
        assertEquals("Name", consumer.getName());
    }

    /**
     * Method under test: {@link PrivacyService#dropAllData(Consumer, String)}
     */
    @Test
    void testDropAllData2() {
        Consumer consumer = mock(Consumer.class);
        when(consumer.getName()).thenReturn("Name");
        doNothing().when(consumer)
                .setId((Long) any());
        doNothing().when(consumer)
                .setName((String) any());
        consumer.setId(1L);
        consumer.setName("Name");
        privacyService.dropAllData(consumer, "Just cause");
        verify(consumer).getName();
        verify(consumer).setId((Long) any());
        verify(consumer).setName((String) any());
    }

    /**
     * Method under test: {@link PrivacyService#dropAllData(Long, String)}
     */
    @Test
    void testDropAllData3() {
        Consumer consumer = new Consumer();
        consumer.setId(1L);
        consumer.setName("Name");
        Optional<Consumer> ofResult = Optional.of(consumer);
        when(consumerRepository.findById((Long) any())).thenReturn(ofResult);
        privacyService.dropAllData(1L, "Just cause");
        verify(consumerRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link PrivacyService#dropAllData(Long, String)}
     */
    @Test
    void testDropAllData4() {
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            when(consumerRepository.findById((Long) any())).thenReturn(Optional.empty());
            privacyService.dropAllData(1L, "Just cause");
        });
    }

    /**
     * Method under test: {@link PrivacyService#dropAllData(Long, String)}
     */
    @Test
    void testDropAllData5() {
        when(consumerRepository.findById((Long) any())).thenThrow(new NoSuchElementException());
        assertThrows(NoSuchElementException.class, () -> privacyService.dropAllData(1L, "Just cause"));
        verify(consumerRepository).findById((Long) any());
    }
}

