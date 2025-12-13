package com.koasleep.core.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Entity
@Table(
        name = "skin_temperatures",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_skin_temp_user_date", columnNames = {"user_id", "date"})
        },
        indexes = {
                @Index(name = "idx_skin_temp_user_date", columnList = "user_id, date")
        }
)
@Getter
@Setter
public class SkinTemperature {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private Double average;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}