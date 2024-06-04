package org.example.service;

import org.example.data.Medicine;
import org.example.dto.MedicineDTO;
import org.example.dto.MedicineResponseDTO;
import org.example.repository.MedicineRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class MedicineServiceTest {

    @Mock
    private MedicineRepository medicineRepository;

    @InjectMocks
    private MedicineService medicineService;

    @Test
    @DisplayName("Add new medicine success")
    void addMedicine() {
        Medicine savedMedicine = Medicine.builder().id(123).name("test").unit("ml").expirationDate(LocalDate.now()).build();
        when(medicineRepository.save(any())).thenReturn(savedMedicine);
        MedicineDTO medicineDTO = MedicineDTO.builder().name("test").build();
        MedicineResponseDTO medicineResponseDTO = medicineService.addMedicine(medicineDTO);
        assertNotNull(medicineResponseDTO);
        assertEquals(savedMedicine.getId(), medicineResponseDTO.getId());
    }
    @Test
    @DisplayName("Get medicine by ID")
    void getMedicine() {
        Integer id = 123;
        Medicine medicine =  Medicine.builder().id(123).name("test").unit("ml").expirationDate(LocalDate.now()).build();
        when(medicineRepository.findById(any())).thenReturn(Optional.of(medicine));
        MedicineResponseDTO result = medicineService.getMedicine(id);
        assertNotNull(result);
        assertEquals(id, result.getId());
    }
}