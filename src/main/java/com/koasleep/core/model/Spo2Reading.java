package com.koasleep.core.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Entity
@Table(
        name = "spo2_readings",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_spo2_user_date", columnNames = {"user_id", "date"})
        },
        indexes = {
                @Index(name = "idx_spo2_user_date", columnList = "user_id, date")
        }
)
@Getter
@Setter
public class Spo2Reading {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate date;

    @Column(name = "avg", nullable = false)
    private Integer avg;

    @Column(name = "min", nullable = false)
    private Integer min;

    @Column(name = "max", nullable = false)
    private Integer max;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}