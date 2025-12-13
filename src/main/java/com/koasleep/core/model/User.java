package com.koasleep.core.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "email", unique = true)
    private String email;
    @Column(name = "full_name", nullable = false)
    private String fullName;
    @Column(name = "display_name")
    private String displayName;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "avatar_url")
    private String avatarUrl;
    @Column(name = "created_at", nullable = false, updatable = false)
    private OffsetDateTime createdAt;
    @Column(name = "updated_at", nullable = false)
    private OffsetDateTime updatedAt;

    // --- RELATIONSHIPS ---

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SleepLog> sleepLogs = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BreathingRate> breathingRates = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HeartRateVariability> heartRateVariabilities = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SkinTemperature> skinTemperatures = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Spo2Reading> spo2Readings = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SmartSummary> smartSummaries = new ArrayList<>();

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private FitbitToken fitbitToken;

    // --- HELPER METHODS ---

    public void addSleepLog(SleepLog log) {
        sleepLogs.add(log);
        log.setUser(this);
    }

    public void removeSleepLog(SleepLog log) {
        sleepLogs.remove(log);
        log.setUser(null);
    }

    public void addBreathingRate(BreathingRate rate) {
        breathingRates.add(rate);
        rate.setUser(this);
    }

    public void setFitbitToken(FitbitToken token) {
        this.fitbitToken = token;
        if (token != null) {
            token.setUser(this);
        }
    }

    // --- LIFECYCLE ---

    @PrePersist
    protected void onCreate() {
        if (this.createdAt == null) this.createdAt = OffsetDateTime.now();
        if (this.updatedAt == null) this.updatedAt = OffsetDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = OffsetDateTime.now();
    }
}