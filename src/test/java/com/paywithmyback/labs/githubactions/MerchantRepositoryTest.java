package com.paywithmyback.labs.githubactions;

import com.paywithmyback.labs.githubactions.data.Merchant;
import com.paywithmyback.labs.githubactions.repos.MerchantRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class MerchantRepositoryTest {

    private static final int MERCHANT_INITIAL_COUNT = 3;
    @Autowired
    private MerchantRepository merchantRepository;

    private Iterable<Merchant> merchants;

    @BeforeEach
    public void setupEach() {
        this.merchants = merchantRepository.findAll();
    }

    @Test
    public void injectedComponentsAreNotNull(){
        assertThat(merchantRepository).isNotNull();
        assertThat(merchants).isNotNull();
    }

    @Test
    public void checkIfThereAre3Merchants() {
        assertThat(merchants).size().isEqualTo(MERCHANT_INITIAL_COUNT);
    }

    @Test
    public void checkIfThereIsSomeMerchantDell() {
        List<Merchant> merchantList = new ArrayList<>();
        merchants.forEach(merchantList::add);
        Optional<Merchant> found = merchantList.stream().filter(m -> "Dell".equalsIgnoreCase(m.getName())).findFirst();
        assertThat(found.get()).isNotNull();
    }

    @Test
    public void checkIfThereIsSomeNonexistentMerchant() {
        List<Merchant> merchantList = new ArrayList<>();
        merchants.forEach(merchantList::add);
        Optional<Merchant> found = merchantList.stream().filter(m -> "Best Buy".equalsIgnoreCase(m.getName())).findFirst();
        assertThat(found.isPresent()).isFalse();
    }

}
