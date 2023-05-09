package com.apm.jacx.model.dtos;

import jakarta.persistence.Column;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class RouteModel {
    private Long id;
    private String name;
    private String begin;
    private List<String> stops;
    private String finish;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
