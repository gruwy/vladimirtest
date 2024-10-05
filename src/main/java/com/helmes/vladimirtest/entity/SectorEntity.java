package com.helmes.vladimirtest.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

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
    @Column(name = "id")
    private Long id;

    @Column(name = "parent_sector_id")
    private Long parentSectorId;

    @Column(name = "sector_name", nullable = false)
    private String sectorName;

    @ManyToMany(mappedBy = "sectors")
    private List<UserEntity> users;

    @Override
    public String toString() {
        return "SectorEntity{" +
                "id=" + id +
                ", parentSectorId=" + parentSectorId +
                ", sectorName='" + sectorName + '\'' +
                '}';
    }
}
