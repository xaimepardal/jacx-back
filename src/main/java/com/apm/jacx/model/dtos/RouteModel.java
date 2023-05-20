package com.apm.jacx.model.dtos;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RouteModel {
    private Long id;
    private String name;
    private String begin;
    private String finish;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

}
