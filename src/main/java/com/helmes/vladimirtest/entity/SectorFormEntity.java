package com.helmes.vladimirtest.entity;

import jakarta.persistence.*;
import lombok.*;


@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="sector_form")
@Table(name="sector_form")
public class SectorFormEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vladimirtest.sector_form_id_seq")
    @SequenceGenerator(name = "vladimirtest.sector_form_id_seq", sequenceName = "vladimirtest.sector_form_id_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "agree_on_terms", nullable = false)
    private boolean agreeOnTerms;

    @Override
    public String toString() {
        return "SectorFormEntity{" +
                "id=" + id +
                ", userId=" + userId +
                ", agreeOnTerms=" + agreeOnTerms +
                '}';
    }
}
