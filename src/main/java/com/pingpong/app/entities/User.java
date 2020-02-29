package com.pingpong.app.entities;

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
import java.util.Set;

import static java.time.Instant.now;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "users")
@TypeDefs({
        @TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String email;

    private String password;

    private String profileImage;

    @Type(type = "jsonb",parameters = {@org.hibernate.annotations.Parameter(name = "listType", value = "java.lang.Integer")})
    private Set<Integer> starred;

    private Boolean isAdmin;

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
