package com.koasleep.core.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Entity
@Table(
        name = "smart_summary", // Matches Prisma model name "smart_summary"
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_summary_user_date", columnNames = {"user_id", "date"})
        }
)
@Getter
@Setter
public class SmartSummary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate date; // Maps to @db.Date

    @Column(nullable = false, columnDefinition = "TEXT")
    private String summary;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}