package com.pingpong.app.entities;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.*;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.Entity;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "users")
@TypeDefs({
        @TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
})
public class User extends BaseEntity {

    private String email;

    private String password;

    private String profileImage;

    @Type(type = "jsonb",parameters = {@org.hibernate.annotations.Parameter(name = "listType", value = "java.lang.Integer")})
    private Set<Integer> starred;

    private Boolean isAdmin;

}
