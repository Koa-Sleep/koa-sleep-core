package com.koasleep.core.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Entity
@Table(
        name = "heart_rate_variabilities",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_hrv_user_date", columnNames = {"user_id", "date"})
        },
        indexes = {
                @Index(name = "idx_hrv_user_date", columnList = "user_id, date")
        }
)
@Getter
@Setter
public class HeartRateVariability {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate date;

    @Column(name = "daily_rmssd", nullable = false)
    private Double dailyRmssd;

    @Column(name = "deep_rmssd", nullable = false)
    private Double deepRmssd;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}