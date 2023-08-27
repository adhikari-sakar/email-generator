package com.machnet.email.application.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.machnet.email.application.mapper.EmailResponseMapper;
import com.machnet.email.application.service.EmailGeneratorService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@WebMvcTest(EmailGenerationController.class)
class EmailGenerationControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private EmailGeneratorService service;
    @MockBean
    private EmailResponseMapper mapper;

    @Test
    @SneakyThrows
    void register_success() {
        RequestBuilder builder = MockMvcRequestBuilders
            .get("/api/v1/email/generate/DEFAULT");

        mockMvc.perform(builder)
            .andExpect(status().isOk());
    }

    @Test
    @SneakyThrows
    void register_notFound() {
        RequestBuilder builder = MockMvcRequestBuilders
            .get("/api/v1/email/generate");

        mockMvc.perform(builder)
            .andExpect(status().isNotFound());
    }

    @Test
    @SneakyThrows
    void register_badRequest() {
        RequestBuilder builder = MockMvcRequestBuilders
            .get("/api/v1/email/generate/xyz");

        mockMvc.perform(builder)
            .andExpect(status().isBadRequest());
    }
}