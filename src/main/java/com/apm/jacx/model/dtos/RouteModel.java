package com.apm.jacx.model.dtos;

import lombok.Data;

import java.time.LocalDate;

@Data
public class RouteModel {
    private Long id;
    private String name;
    private WaypointModel begin;
    private WaypointModel finish;
    private LocalDate startDate;
    private LocalDate endDate;

}
