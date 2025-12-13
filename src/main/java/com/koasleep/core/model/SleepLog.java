package com.koasleep.core.model;

import jakarta.persistence.*;
import lombok.Getter; // Changed
import lombok.Setter; // Changed
import java.time.LocalDate;
import java.time.OffsetDateTime;

@Entity
@Table(
        name = "sleep_logs",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_sleep_user_date", columnNames = {"user_id", "date"})
        },
        indexes = {
                @Index(name = "idx_sleep_user_date", columnList = "user_id, date")
        }
)
@Getter
@Setter
public class SleepLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate date;

    @Column(name = "bed_time", nullable = false)
    private OffsetDateTime bedTime;

    @Column(name = "wake_time", nullable = false)
    private OffsetDateTime wakeTime;

    @Column(name = "duration_ms", nullable = false)
    private Long durationMs;

    @Column(nullable = false)
    private Integer efficiency;

    @Column(name = "awake_mins", nullable = false)
    private Integer awakeMins;

    @Column(name = "light_mins", nullable = false)
    private Integer lightMins;

    @Column(name = "deep_mins", nullable = false)
    private Integer deepMins;

    @Column(name = "rem_mins", nullable = false)
    private Integer remMins;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}