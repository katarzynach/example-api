package org.example.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.MedicineDTO;
import org.example.dto.MedicineResponseDTO;
import org.example.service.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping(value = "/medicine")

public class MedicineController {

    @Autowired
    private MedicineService medicineService;


    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Add new medicine")
    public ResponseEntity<MedicineResponseDTO> addMedicine(@RequestBody @Valid MedicineDTO medicineDTO){
        return new ResponseEntity<>(medicineService.addMedicine(medicineDTO), HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get medicine")
    public ResponseEntity<MedicineResponseDTO> getMedicine(@PathVariable Integer id){
        return new ResponseEntity<>(medicineService.getMedicine(id), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Delete medicine")
    public ResponseEntity<Void> deleteMedicine(@PathVariable Integer id){
        medicineService.deleteMedicine(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
