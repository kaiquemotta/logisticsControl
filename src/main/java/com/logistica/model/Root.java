package com.logistica.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Root {
    private List<Vehicle> vehicles;
    private List<VehicleType> vehicle_types;
    private List<Service> services;

}

