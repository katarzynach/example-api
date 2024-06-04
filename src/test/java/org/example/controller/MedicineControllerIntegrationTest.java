package org.example.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.dto.MedicineDTO;
import org.example.dto.MedicineResponseDTO;
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
class MedicineControllerIntegrationTest {


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("Add new medicine when valid request body")
    void addMedicineSuccess() throws Exception {
        MedicineDTO medicineDTO = MedicineDTO.builder()
                .name("test").manufacturer("testM").amount(22.5).unit("ml").expirationDate(LocalDate.now()).build();
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/medicine/add")
                        .content(objectMapper.writeValueAsString(medicineDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();

        MedicineResponseDTO created = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), MedicineResponseDTO.class);

        assertNotNull(created.getId());
        assertEquals(medicineDTO.getName(), created.getName());
    }

    @Test
    @DisplayName("Add new medicine when invalid request body")
    void addMedicineShouldFail() throws Exception {
        MedicineDTO medicineDTO = MedicineDTO.builder()
                .manufacturer("testM").amount(22.5).unit("ml").expirationDate(LocalDate.now()).build();
        mockMvc.perform(MockMvcRequestBuilders.post("/medicine/add")
                        .content(objectMapper.writeValueAsString(medicineDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Get medicine by ID")
    void getMedicine() throws Exception {
        Integer id = 1;
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/medicine/" + id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        MedicineResponseDTO returned = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), MedicineResponseDTO.class);
        assertNotNull(returned.getId());
        assertEquals((id), returned.getId());

    }

    @Test
    @DisplayName("Get medicine when ID Not Found")
    void getMedicineNotFound() throws Exception {
        Integer id = Integer.MAX_VALUE;
        mockMvc.perform(MockMvcRequestBuilders.get("/medicine/" + id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }
}