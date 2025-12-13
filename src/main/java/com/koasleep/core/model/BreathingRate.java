package com.koasleep.core.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Entity
@Table(
        name = "breathing_rates",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_user_date", columnNames = {"user_id", "date"})
        },
        indexes = {
                @Index(name = "idx_user_date", columnList = "user_id, date")
        }
)
@Getter
@Setter
public class BreathingRate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate date;

    @Column(name = "breathing_rate", nullable = false)
    private Double breathingRate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}