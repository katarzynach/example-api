package org.example.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDate;

@Builder
@Data
@Getter
public class SearchItemDTO {

    private Integer id;
    private String name;
    private String manufacturer;
    private Double amount;
    private String unit;
    private LocalDate fromDate;
    private LocalDate toDate;
    private String additionalInfo;
}
