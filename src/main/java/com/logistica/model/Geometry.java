package com.logistica.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Geometry {
    private Bounds bounds = new Bounds();
    private Location location;
    private String location_type;
    private ViewPort viewport;
}
