package com.logistica.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Bounds {

    private Northeast northeast = new Northeast();
    private Southwest southwest;
}
