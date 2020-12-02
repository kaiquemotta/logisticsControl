package com.logistica.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Results {
    private List<AddressComponents> address_components;
    private String formatted_address;
    private Geometry geometry;
    private String place_id;
}
