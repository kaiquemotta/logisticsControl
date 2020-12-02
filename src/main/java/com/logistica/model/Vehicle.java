package com.logistica.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Vehicle {
    private String vehicle_id;
    private StartAddress start_address;
    private String type_id;
    private boolean return_to_depot;
}
