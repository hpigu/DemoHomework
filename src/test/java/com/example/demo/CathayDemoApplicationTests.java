package com.example.demo;

import com.example.demo.model.request.dm010201.DM010201Req;
import com.example.demo.model.request.dm010301.DM010301Req;
import com.example.demo.model.request.dm010401.DM010401Req;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class CathayDemoApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @Order(1)
    void queryCoinDesk() throws Exception {
        mockMvc.perform(get("/DM010101")
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.time").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.bpi").isNotEmpty());
    }

    @Test
    @Order(2)
    void queryCoinConvert() throws Exception {
        mockMvc.perform(get("/DM010102")
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.convertCoinList").isNotEmpty());
    }

    @Test
    @Order(3)
    void queryCoin() throws Exception {
        mockMvc.perform(get("/DM010103").param("currency","USD")
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.coin").isNotEmpty());
    }

    @Test
    @Order(4)
    void insertCoin() throws Exception {
        DM010201Req dm010201Req = new DM010201Req();
        dm010201Req.setCurrency("NTD");
        dm010201Req.setRate(1511.45f);
        dm010201Req.setCurrencyChinese("新台幣");
        String content = objectMapper.writeValueAsString(dm010201Req);

        mockMvc.perform(post("/DM010201").content(content)
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.saveResult").value("Y"));
    }

    @Test
    @Order(5)
    void updateCoin() throws Exception {
        DM010301Req dm010301Req = new DM010301Req();
        dm010301Req.setCurrency("USD");
        dm010301Req.setRate(42320.92f);
        String content = objectMapper.writeValueAsString(dm010301Req);

        mockMvc.perform(post("/DM010301").content(content)
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.updateResult").value("Y"));
    }

    @Test
    @Order(6)
    void deleteCoin() throws Exception {
        DM010401Req dm010401Req = new DM010401Req();
        dm010401Req.setCurrency("NTD");
        String content = objectMapper.writeValueAsString(dm010401Req);

        mockMvc.perform(post("/DM010401").content(content)
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.deleteResult").value("Y"));
    }
}
