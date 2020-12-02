package com.logistica.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RootRetorno {
    public List<String> copyrights;
    public String job_id;
    public String status;
    public int waiting_time_in_queue;
    public int processing_time;
    public Solution solution;
}
