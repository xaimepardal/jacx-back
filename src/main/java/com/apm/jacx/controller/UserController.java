package com.apm.jacx.controller;

import com.apm.jacx.model.User;
import com.apm.jacx.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping(value = "/user")
    private ResponseEntity<User> create(@RequestBody User user) {
        try {
            User tmp = userService.create(user);
            return ResponseEntity.created(new URI("api/user/" + tmp.getId())).body(tmp);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }


    @GetMapping(value = "/users")
    private ResponseEntity<List<User>> getAll() {
        try {
            List<User> tmp = userService.getAllUsers();
            return ResponseEntity.ok(tmp);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }


    @DeleteMapping(value = "/user")
    private ResponseEntity<Void> delete(@RequestBody User user) {
        try {
            userService.delete(user);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }


    @GetMapping(value = "/user/{id}")
    private ResponseEntity<Optional<User>> getByID(@PathVariable("id") Long id) {
        try {
            Optional<User> tmp = userService.findById(id);
            return ResponseEntity.ok(tmp);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

}
