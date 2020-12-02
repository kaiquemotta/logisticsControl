package com.logistica.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Route {
    public String vehicle_id;
    public String shift_id;
    public int distance;
    public int transport_time;
    public int completion_time;
    public int waiting_time;
    public int service_duration;
    public int preparation_time;
    public List<Activity> activities;
}
