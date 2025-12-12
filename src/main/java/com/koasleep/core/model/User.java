package com.koasleep.core.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID) // Auto-generate UUIDs
    private UUID id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(name = "password_hash") // Map camelCase to snake_case column
    private String passwordHash;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @PrePersist // Runs before saving to DB
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}