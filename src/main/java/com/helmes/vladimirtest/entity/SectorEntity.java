package com.helmes.vladimirtest.entity;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="sector")
@Table(name="sector")
public class SectorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vladimirtest.sector_id_seq")
    @SequenceGenerator(name = "vladimirtest.sector_id_seq", sequenceName = "vladimirtest.sector_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "sector_name", nullable = false)
    private String sectorName;

    @Override
    public String toString() {
        return "SectorEntity{" +
                "id=" + id +
                ", sectorName='" + sectorName + '\'' +
                '}';
    }
}
