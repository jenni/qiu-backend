package com.pingpong.app.entities;

import com.pingpong.app.models.Coordinates;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.*;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "ping_pong_tables")
@TypeDefs({
        @TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
})
public class PingPongTable extends BaseEntity {

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
}
