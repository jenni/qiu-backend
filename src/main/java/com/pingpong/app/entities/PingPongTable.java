package com.pingpong.app.entities;

import com.google.maps.model.GeocodingResult;
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
    private Coordinates coordinates;

    private String description;

    private String imageUrl;

    private Boolean hasLight;

    private Boolean isIndoor;

    private Boolean isSportsClub;

    private Boolean isBar;

    private Integer createdBy;

    @Type(type = "jsonb")
    private GeocodingResult[] geoApi;

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
