package com.etra.reactiveNotes.reactiveNotes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("unittest")
@ContextConfiguration(classes = {TestConfig.class})
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureMockMvc
public abstract class BaseTest {

    protected MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;


    public static <T> T toObject(MvcResult mvcResult, Class<T> clazz) {
        try {
            return new ObjectMapper().readValue(mvcResult.getResponse().getContentAsString(), clazz);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public static String toString(Object object) {
        try {
            return new ObjectMapper().writeValueAsString(object);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @BeforeEach
    public void initialization() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
        this.init();
    }

    public abstract void init();

}

