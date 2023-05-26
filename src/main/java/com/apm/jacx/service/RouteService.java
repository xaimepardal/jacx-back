package com.apm.jacx.service;

import com.apm.jacx.model.AppUser;
import com.apm.jacx.model.Route;
import com.apm.jacx.model.WayPoint;
import com.apm.jacx.model.dtos.RouteModel;
import com.apm.jacx.model.dtos.WaypointModel;
import com.apm.jacx.repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RouteService {
    @Autowired
    private RouteRepository routeRepository;

    public Route create(RouteModel routeModel, AppUser user) {

        Route route = new Route();
        if (routeModel.getName() != null) {
            route.setName(routeModel.getName());
        }
        if (routeModel.getBegin() != null) {
            route.setBegin(getWayPoint(routeModel.getBegin()));
        }
        if (routeModel.getFinish() != null) {
            route.setFinish(getWayPoint(routeModel.getFinish()));
        }
        if (routeModel.getStartDate() != null) {
            route.setStartDate(routeModel.getStartDate());
        }
        if (routeModel.getEndDate() != null) {
            route.setEndDate(routeModel.getEndDate());
        }
        route.setAppUser(user);
        return routeRepository.saveAndFlush(route);
    }

    public List<Route> getAll() {
        return routeRepository.findAll();
    }

    public void delete(Route route) {
        routeRepository.delete(route);
    }

    public List<Route> findByOwner(AppUser user) {
        return routeRepository.findAllByAppUser(user);
    }

    public Route findByName(String name) {
        return routeRepository.findByName(name);
    }
    
    public Route findById(Long id) {
		return routeRepository.findById(id).orElse(null);
	}

    public Route update(RouteModel routeModel) {
        Route route = routeRepository.getReferenceById(routeModel.getId());
        if (routeModel.getName() != null) {
            route.setName(routeModel.getName());
        }
        if (routeModel.getBegin() != null) {
            route.setBegin(getWayPoint(routeModel.getBegin()));
        }
        if (routeModel.getFinish() != null) {
            route.setFinish(getWayPoint(routeModel.getFinish()));
        }
        if (routeModel.getStartDate() != null) {
            route.setStartDate(routeModel.getStartDate());
        }
        if (routeModel.getEndDate() != null) {
            route.setEndDate(routeModel.getEndDate());
        }
        return routeRepository.saveAndFlush(route);
    }

    public Route update(Route route) {
        return routeRepository.saveAndFlush(route);
    }
    
    private WayPoint getWayPoint(WaypointModel waypointModel) {
    	WayPoint waypoint = new WayPoint();
    	waypoint.setId(waypointModel.getId());
    	waypoint.setName(waypointModel.getName());
    	waypoint.setOrderPosition(waypointModel.getOrderPosition());
    	waypoint.setPoint(waypointModel.getPoint());
    	waypoint.setColor(waypointModel.getColor());
    	waypoint.setUrl(waypointModel.getUrl());
    	return waypoint;
    }

}
