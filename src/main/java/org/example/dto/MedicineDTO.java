package org.example.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Builder
@Data
public class MedicineDTO implements Serializable {
//todo validation add messages
    @NotEmpty
    private String name;
    @NotEmpty
    private String manufacturer;
    @NotNull
    private Double amount;
    @NotEmpty
    private String unit;
    @NotNull
    private LocalDate expirationDate;
    private String additionalInfo;
}
