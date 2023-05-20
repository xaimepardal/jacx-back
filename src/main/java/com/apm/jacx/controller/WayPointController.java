package com.apm.jacx.controller;

import com.apm.jacx.model.AppUser;
import com.apm.jacx.model.Image;
import com.apm.jacx.model.Route;
import com.apm.jacx.model.WayPoint;
import com.apm.jacx.model.dtos.RouteModel;
import com.apm.jacx.service.AppUserService;
import com.apm.jacx.service.WayPointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class WayPointController {

    @Autowired
    WayPointService wayPointService;

    @Autowired
    private AppUserService userService;

    @PostMapping(value = "/waypoint")
    private ResponseEntity<WayPoint> create(@RequestBody WayPoint wayPoint, @RequestHeader(HttpHeaders.AUTHORIZATION) String token) throws URISyntaxException {
        AppUser appUser = userService.checkToken(token);
        if (appUser != null) {
            WayPoint tmp = wayPointService.create(wayPoint);
            return ResponseEntity.created(new URI("api/waypoint/" + tmp.getId())).body(tmp);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PutMapping(value = "/waypoint")
    private ResponseEntity<WayPoint> update(@RequestBody WayPoint wayPoint) {
        WayPoint tmp = wayPointService.update(wayPoint);
        return ResponseEntity.ok(tmp);
    }

    @GetMapping(value = "/waypoints")
    private ResponseEntity<List<WayPoint>> getAll(@RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        AppUser appUser = userService.checkToken(token);
        if (appUser != null) {
            List<WayPoint> tmp = wayPointService.getAll();
            return ResponseEntity.ok(tmp);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @DeleteMapping(value = "/waypoint")
    private ResponseEntity<Void> delete(@RequestBody WayPoint wayPoint, @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        AppUser appUser = userService.checkToken(token);
        if (appUser != null) {
            wayPointService.delete(wayPoint);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
