package org.example.service;


import lombok.extern.slf4j.Slf4j;
import org.example.data.Medicine;
import org.example.dto.MedicineDTO;
import org.example.dto.MedicineResponseDTO;
import org.example.repository.MedicineRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Slf4j
@Service
public class MedicineService {

    @Autowired
    private MedicineRepository medicineRepository;

    public MedicineResponseDTO addMedicine(MedicineDTO medicineDTO){
        Medicine entity = new Medicine();
        BeanUtils.copyProperties(medicineDTO, entity);
        Medicine medicine = medicineRepository.save(entity);
        MedicineResponseDTO medicineResponseDTO = new MedicineResponseDTO();
        BeanUtils.copyProperties(medicine, medicineResponseDTO);
        return medicineResponseDTO;
    }

    public MedicineResponseDTO getMedicine(Integer id){
        Optional<Medicine> response = medicineRepository.findById(id);
        if(response.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not Found: " + id);
        }
        MedicineResponseDTO medicineResponseDTO = new MedicineResponseDTO();
        BeanUtils.copyProperties(response.get(), medicineResponseDTO);
        return medicineResponseDTO;
    }

    public void deleteMedicine(Integer id){
        Optional<Medicine> response = medicineRepository.findById(id);
        if(response.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not Found: " + id);
        }
        medicineRepository.delete(response.get());
    }
}
