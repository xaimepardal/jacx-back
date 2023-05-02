package com.apm.jacx.controller;

import com.apm.jacx.model.AppUser;
import com.apm.jacx.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class AppUserController {

    @Autowired
    private AppUserService userService;


    @PostMapping(value = "/user")
    private ResponseEntity<AppUser> create(@RequestBody AppUser appUser) {
        try {
            AppUser tmp = userService.create(appUser);
            return ResponseEntity.created(new URI("api/user/" + tmp.getId())).body(tmp);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }


    @GetMapping(value = "/users")
    private ResponseEntity<List<AppUser>> getAll() {
        try {
            List<AppUser> tmp = userService.getAllUsers();
            return ResponseEntity.ok(tmp);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }


    @DeleteMapping(value = "/user")
    private ResponseEntity<Void> delete(@RequestBody AppUser appUser) {
        try {
            userService.delete(appUser);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }


    @GetMapping(value = "/user/{id}")
    private ResponseEntity<Optional<AppUser>> getByID(@PathVariable("id") Long id) {
        try {
            Optional<AppUser> tmp = userService.findById(id);
            return ResponseEntity.ok(tmp);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

}
