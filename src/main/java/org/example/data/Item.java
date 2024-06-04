package org.example.data;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.time.LocalDate;

@Data
@Builder
@Table(name = "meds")
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Item {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "MANUFACTURER")
    private String manufacturer;
    @Column(name = "AMOUNT")
    private Double amount;
    @Column(name = "UNIT")
    private String unit;
    @Column(name = "EXPIRATION_DATE")
    private LocalDate expirationDate;
    @Column(name = "ADDITIONAL_INFO")
    private String additionalInfo;
    @Column(name = "CREATION_DATE")
    @CreationTimestamp
    private Instant creationDate;
}
