package com.pingpong.app.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;

import static java.time.Instant.now;

@Data
@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Instant updatedAt;

    private Instant createdAt;

    @PrePersist
    public void onPrePersist() {
        updatedAt = now();
        createdAt = now();
    }

    @PreUpdate
    public void onPreUpdate() {
        updatedAt = now();
    }
}
