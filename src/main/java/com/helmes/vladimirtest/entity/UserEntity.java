package com.helmes.vladimirtest.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="user")
@Table(name="user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vladimirtest.user_id_seq")
    @SequenceGenerator(name = "vladimirtest.user_id_seq", sequenceName = "vladimirtest.user_id_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_name", nullable = false, unique = true)
    private String userName;

    @Column(name = "agreed_to_terms", nullable = false)
    private boolean agreedToTerms;

    @ManyToMany
    @JoinTable(name = "user_sector", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "sector_id"))
    private List<SectorEntity> sectors;

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", agreedToTerms=" + agreedToTerms +
                ", sectors=" + sectors +
                '}';
    }
}
