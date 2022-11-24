package com.paywithmyback.labs.githubactions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.paywithmyback.labs.githubactions.data.Merchant;
import com.paywithmyback.labs.githubactions.repos.MerchantRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.hasToString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@ExtendWith(SpringExtension.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = ArchitectureGithubActionsApplication.class
)
@AutoConfigureMockMvc
public class MerchantRestRepositoryTest {

    private static final int MERCHANT_INITIAL_COUNT = 3;
    @Autowired
    private MockMvc mvc;

    @Autowired
    private MerchantRepository merchantRepository;

    @Test
    public void givenCreateMerchantAndThenListOfMerchantsThenReturnsJsonArray() throws Exception {
        mvc.perform(put("/merchants/{id}", 4L)
                            .content(
                                    asJsonString(
                                            new Merchant(4L, "Mercado Libre")
                                    )
                            )
                            .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is2xxSuccessful());

        mvc.perform(get("/merchants").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._embedded.merchants", hasSize(MERCHANT_INITIAL_COUNT+1)));
    }

    @Test
    public void givenCreateMerchantAndThenReturnsMercadoLibreInFirstPlace() throws Exception {
        mvc.perform(put("/merchants/{id}", 4L)
                            .content(
                                    asJsonString(
                                            new Merchant(4L, "Mercado Libre")
                                    )
                            )
                            .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is2xxSuccessful());

        mvc.perform(get("/merchants").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._embedded.merchants", hasSize(MERCHANT_INITIAL_COUNT+1)))
                .andExpect(jsonPath("$._embedded.merchants[0].name", hasToString("Dell")));
    }

    @Test
    public void givenCreateMerchantAndThenReturnsMercadoLibreInFourthPlace() throws Exception {
        mvc.perform(put("/merchants/{id}", 4L)
                            .content(
                                    asJsonString(
                                            new Merchant(4L, "Mercado Libre")
                                    )
                            )
                            .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is2xxSuccessful());

        mvc.perform(get("/merchants").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._embedded.merchants", hasSize(MERCHANT_INITIAL_COUNT+1)))
                .andExpect(jsonPath("$._embedded.merchants[3].name", hasToString("Mercado Libre")));
    }

    @Test
    public void givenIdForEbayThenGetById() throws Exception {
        mvc.perform(get("/merchants/{id}","2").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", hasToString("Ebay")));
    }

    @Test
    public void givenNewMerchantThenCreateWalmart() throws Exception {
        mvc.perform(post("/merchants")
                            .content(
                                    asJsonString(
                                            new Merchant(5L, "Walmart")
                                    )
                            )
                            .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isCreated());
    }

    @Test
    public void givenWalmartNameUpdateThenUpdate() throws Exception {
        mvc.perform(put("/merchants/{id}", 5L)
                            .content(
                                    asJsonString(
                                            new Merchant(5L, "Walmart*")
                                    )
                            )
                            .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is2xxSuccessful());

        mvc.perform(put("/merchants/{id}", 5L)
                            .content(
                                    asJsonString(
                                            new Merchant(5L, "Walmart")
                                    )
                            )
                            .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void givenMercadoLibreIdThenDelete() throws Exception {
        mvc.perform(put("/merchants/{id}", 4L)
                            .content(
                                    asJsonString(
                                            new Merchant(4L, "Mercado Libre")
                                    )
                            )
                            .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is2xxSuccessful());

        mvc.perform(delete("/merchants/{id}", 4L)
                            .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is2xxSuccessful());
    }

    private String asJsonString(Merchant m) {
        try {
            return new ObjectMapper().writeValueAsString(m);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
