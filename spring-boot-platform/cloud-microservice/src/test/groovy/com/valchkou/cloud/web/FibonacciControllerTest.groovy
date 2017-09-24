package com.valchkou.cloud.web

import com.fasterxml.jackson.databind.ObjectMapper
import com.valchkou.cloud.Main
import com.valchkou.cloud.model.CalcMode
import com.valchkou.cloud.service.FibonacciService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by evalchkou on 9/23/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Main.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@AutoConfigureMockMvc
class FibonacciControllerTest {

    private static final String BASE_URL = "/api/fibonacci"

    @Autowired
    private MockMvc mvc

    @Autowired
    ObjectMapper objectMapper

    @Test
    public void testCalcNSuccess() throws Exception {
        int n = 10;
        String url = BASE_URL+"/$n"

        mvc.perform(get(url)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath('$.result', is(55)))
    }

    @Test
    void testCalcNLoop() throws Exception {
        int n = 10;
        String mode = CalcMode.LOOP.name()
        String url = BASE_URL+"/$n/$mode"

        mvc.perform(get(url)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath('$.result', is(55)))
    }

    @Test
    void testCalcNRecursive() throws Exception {
        int n = 10;
        String mode = CalcMode.RECURSIVE.name()
        String url = BASE_URL+"/$n/$mode"

        mvc.perform(get(url)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath('$.result', is(55)))
    }

    @Test
    void testCalcNScientific() throws Exception {
        int n = 10;
        String mode = CalcMode.SCIENTIFIC.name()
        String url = BASE_URL+"/$n/$mode"
        mvc.perform(get(url)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath('$.result', is(55)))
    }

    @Test
    void testCalcNUnknown() throws Exception {
        int n = 10;
        String url = BASE_URL+"/$n/Wrong"
        mvc.perform(get(url)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError())

    }

}
