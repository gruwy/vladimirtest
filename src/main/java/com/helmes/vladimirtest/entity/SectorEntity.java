package com.helmes.vladimirtest.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sector_id_seq")
    @SequenceGenerator(name = "sector_id_seq", sequenceName = "sector_id_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "parent_sector_id")
    private SectorEntity parentSector;

    @OneToMany(mappedBy = "parentSector")
    private List<SectorEntity> subSectors;

    @Column(name = "sector_name", nullable = false)
    private String sectorName;

    @ManyToMany(mappedBy = "sectors")
    private List<UserEntity> users;

    @Override
    public String toString() {
        return "SectorEntity{" +
                "id=" + id +
                ", parentSector=" + parentSector +
                ", sectorName='" + sectorName + '\'' +
                '}';
    }
}
