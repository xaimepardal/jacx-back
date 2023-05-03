package com.apm.jacx.service;

import com.apm.jacx.model.Images;
import com.apm.jacx.model.Route;
import com.apm.jacx.repository.ImagesRepository;
import com.apm.jacx.repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RouteService {
    @Autowired
    private RouteRepository routeRepository;

    public Route create (Route route) {
        return routeRepository.save(route);
    }

    public List<Route> getAll() {
        return routeRepository.findAll();
    }

    public void delete (Route route) {
        routeRepository.delete(route);
    }

    public Optional<Route> findById (Long id) {
        return routeRepository.findById(id);
    }
}
