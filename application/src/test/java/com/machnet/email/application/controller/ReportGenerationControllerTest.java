package com.machnet.email.application.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.machnet.email.application.component.ReportGenerator;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@WebMvcTest(ReportGenerationController.class)
class ReportGenerationControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ReportGenerator generator;

    @Test
    @SneakyThrows
    void generate_success() {
        RequestBuilder builder = MockMvcRequestBuilders
            .post("/api/v1/report/generate");

        mockMvc.perform(builder)
            .andExpect(status().isAccepted());
    }

    @Test
    @SneakyThrows
    void generator_notFound() {
        RequestBuilder builder = MockMvcRequestBuilders
            .post("/api/v1/report");

        mockMvc.perform(builder)
            .andExpect(status().isNotFound());
    }
}