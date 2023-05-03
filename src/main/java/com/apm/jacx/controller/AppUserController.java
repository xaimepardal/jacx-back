package com.apm.jacx.controller;

import com.apm.jacx.model.AppUser;
import com.apm.jacx.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class AppUserController {

    @Autowired
    private AppUserService userService;

    @PostMapping(value = "/user")
    private ResponseEntity<AppUser> create(@RequestBody AppUser appUser) throws URISyntaxException {
        AppUser tmp = userService.create(appUser);
        return ResponseEntity.created(new URI("api/user/" + tmp.getId())).body(tmp);
    }

    @GetMapping(value = "/users")
    private ResponseEntity<List<AppUser>> getAll() {
        List<AppUser> tmp = userService.getAll();
        return ResponseEntity.ok(tmp);
    }

    @DeleteMapping(value = "/user")
    private ResponseEntity<Void> delete(@RequestBody AppUser appUser) {
        userService.delete(appUser);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/user/{id}")
    private ResponseEntity<Optional<AppUser>> getByID(@PathVariable("id") Long id) {
        Optional<AppUser> tmp = userService.findById(id);
        return ResponseEntity.ok(tmp);
    }

}
