package com.pingpong.app.entities;

import com.pingpong.app.models.Coordinates;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;
import java.time.Instant;

import static java.time.Instant.now;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "ping_pong_tables")
@TypeDefs({
        @TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
})
public class PingPongTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String address;

    private String city;

    @Type(type = "jsonb")
    @Column(name = "coordinates", columnDefinition = "jsonb")
    private Coordinates coordinates;

    private String description;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "has_light")
    private Boolean hasLight;

    @Column(name = "is_indoor")
    private Boolean isIndoor;

    @Column(name = "is_sports_club")
    private Boolean isSportsClub;

    @Column(name = "is_bar")
    private String isBar;

    @Column(name = "createdBy")
    private Integer createdBy;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;

    @PrePersist
    public void onPrePersist() {
        createdAt = now();
        updatedAt = now();
    }

    @PreUpdate
    public void onPreUpdate() {
        updatedAt = now();
    }
}
