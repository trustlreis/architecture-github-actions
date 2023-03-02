package com.paywithmyback.labs.githubactions.repos;

import static org.junit.jupiter.api.Assertions.assertNull;

import com.paywithmyback.labs.githubactions.data.Merchant;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = {MerchantRepository.class})
@EnableAutoConfiguration
@EntityScan(basePackages = {"com.paywithmyback.labs.githubactions.data"})
@DataJpaTest
class MerchantRepositoryTest {

    @Autowired
    private MerchantRepository merchantRepository;

    /**
     * Method under test: {@link MerchantRepository#findByName(String)}
     */
    @Test
    void testFindByName() {
        Merchant merchant = new Merchant();
        merchant.setId(1L);
        merchant.setName("Name");

        Merchant merchant1 = new Merchant();
        merchant1.setId(1L);
        merchant1.setName("Name");
        merchantRepository.save(merchant);
        merchantRepository.save(merchant1);
        assertNull(merchantRepository.findByName("foo"));
    }
}

