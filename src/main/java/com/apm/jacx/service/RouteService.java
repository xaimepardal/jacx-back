package com.apm.jacx.service;

import com.apm.jacx.model.AppUser;
import com.apm.jacx.model.Image;
import com.apm.jacx.model.Route;
import com.apm.jacx.model.dtos.RouteModel;
import com.apm.jacx.repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
            route.setBegin(routeModel.getBegin());
        }
        if (routeModel.getStops() != null) {
            route.setStops(routeModel.getStops());
        }
        if (routeModel.getFinish() != null) {
            route.setFinish(routeModel.getFinish());
        }
        if (routeModel.getStartDate() != null) {
            route.setStartDate(routeModel.getStartDate());
        }
        if (routeModel.getEndDate() != null) {
            route.setEndDate(routeModel.getEndDate());
        }
        route.setOwner(user);
        return routeRepository.save(route);
    }

    public List<Route> getAll() {
        return routeRepository.findAll();
    }

    public void delete(Route route) {
        routeRepository.delete(route);
    }

    public List<Route> findByOwner(AppUser user) {
        return routeRepository.findAllByOwner(user);
    }

    public Route findByName(String name) {
        return routeRepository.findByName(name);
    }

    public Route update(RouteModel routeModel) {
        Route route = routeRepository.getReferenceById(routeModel.getId());
        if (routeModel.getName() != null) {
            route.setName(routeModel.getName());
        }
        if (routeModel.getBegin() != null) {
            route.setBegin(routeModel.getBegin());
        }
        if (routeModel.getStops() != null) {
            route.setStops(routeModel.getStops());
        }
        if (routeModel.getFinish() != null) {
            route.setFinish(routeModel.getFinish());
        }
        if (routeModel.getStartDate() != null) {
            route.setStartDate(routeModel.getStartDate());
        }
        if (routeModel.getEndDate() != null) {
            route.setEndDate(routeModel.getEndDate());
        }
        return routeRepository.save(route);
    }

    public Route update(Route route) {
        System.out.println("Update route: "+route);
        return routeRepository.save(route);
    }

}
