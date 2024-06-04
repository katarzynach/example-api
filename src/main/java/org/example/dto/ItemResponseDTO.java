package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemResponseDTO implements Serializable {

    private Integer id;
    private String name;
    private String manufacturer;
    private Double amount;
    private String unit;
    private LocalDate expirationDate;
    private String additionalInfo;
    private Instant creationDate;
}
