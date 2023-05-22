package com.apm.jacx.model.dtos;

import lombok.Data;

@Data
public class WaypointModel {

	private Long id;
    private String name;
    private Integer orderPosition;
    private String point;
    private String url;
    private Integer color;
}
