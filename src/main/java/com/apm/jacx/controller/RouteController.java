package com.apm.jacx.controller;

import com.apm.jacx.model.Route;
import com.apm.jacx.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class RouteController {
    @Autowired
    private RouteService routeService;

    @PostMapping(value = "/route")
    private ResponseEntity<Route> create(@RequestBody Route route) throws URISyntaxException {
        Route tmp = routeService.create(route);
        return ResponseEntity.created(new URI("api/route/" + tmp.getId())).body(tmp);
    }

    @PutMapping(value = "/route")
    private ResponseEntity<Route> update(@RequestBody Route route) {
        Route tmp = routeService.create(route);
        return ResponseEntity.ok(tmp);
    }

    @GetMapping(value = "/routes")
    private ResponseEntity<List<Route>> getAll() {
        List<Route> tmp = routeService.getAll();
        return ResponseEntity.ok(tmp);
    }

    @DeleteMapping(value = "/route")
    private ResponseEntity<Void> delete(@RequestBody Route route) {
        routeService.delete(route);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/route/{id}")
    private ResponseEntity<Optional<Route>> getByID(@PathVariable("id") Long id) {
        Optional<Route> tmp = routeService.findById(id);
        return ResponseEntity.ok(tmp);
    }
}
