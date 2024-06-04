package org.example.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.dto.ItemDTO;
import org.example.dto.ItemResponseDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ItemControllerIntegrationTest {


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("Add new item when valid request body")
    void addItemSuccess() throws Exception {
        ItemDTO itemDTO = ItemDTO.builder()
                .name("test").manufacturer("testM").amount(22.5).unit("ml").expirationDate(LocalDate.now()).build();
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/item/add")
                        .content(objectMapper.writeValueAsString(itemDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();

        ItemResponseDTO created = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), ItemResponseDTO.class);

        assertNotNull(created.getId());
        assertEquals(itemDTO.getName(), created.getName());
    }

    @Test
    @DisplayName("Add new item when invalid request body")
    void addItemShouldFail() throws Exception {
        ItemDTO itemDTO = ItemDTO.builder()
                .manufacturer("testM").amount(22.5).unit("ml").expirationDate(LocalDate.now()).build();
        mockMvc.perform(MockMvcRequestBuilders.post("/item/add")
                        .content(objectMapper.writeValueAsString(itemDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Get item by ID")
    void getItem() throws Exception {
        Integer id = 1;
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/item/" + id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        ItemResponseDTO returned = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), ItemResponseDTO.class);
        assertNotNull(returned.getId());
        assertEquals((id), returned.getId());

    }

    @Test
    @DisplayName("Get item when ID Not Found")
    void getItemNotFound() throws Exception {
        Integer id = Integer.MAX_VALUE;
        mockMvc.perform(MockMvcRequestBuilders.get("/item/" + id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }
}