package com.logistica.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Address {
    private String location_id;
    private double lon;
    private double lat;
}
