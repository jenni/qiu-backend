package com.pingpong.app.models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Coordinates {

    @NotNull private Double lat;
    @NotNull private Double lng;
}
