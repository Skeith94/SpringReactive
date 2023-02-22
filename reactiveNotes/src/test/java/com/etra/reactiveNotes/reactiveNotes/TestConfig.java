package com.etra.reactiveNotes.reactiveNotes;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
@ComponentScan("com.etra.reactiveNotes.reactiveNotes")
@EnableWebMvc
public class TestConfig {
    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}
