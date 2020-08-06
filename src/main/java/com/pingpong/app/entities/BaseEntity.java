package com.pingpong.app.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;

import static java.time.Instant.now;
import static javax.persistence.GenerationType.IDENTITY;

@Data
@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    private Instant updatedAt;

    private Instant createdAt;

    @PrePersist
    public void onPrePersist() {
        Instant now = now();
        updatedAt = now;
        createdAt = now;
    }

    @PreUpdate
    public void onPreUpdate() {
        updatedAt = now();
    }
}
