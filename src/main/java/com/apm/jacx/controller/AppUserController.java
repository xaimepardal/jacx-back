package com.apm.jacx.controller;

import ch.qos.logback.core.joran.action.AppenderAction;
import com.apm.jacx.model.AppUser;
import com.apm.jacx.model.ChangePasswordModel;
import com.apm.jacx.service.AppUserService;
import com.google.common.hash.Hashing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
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

    @PutMapping(value = "/user")
    private ResponseEntity<AppUser> update(@RequestBody AppUser appUser) {
        AppUser tmp = userService.update(appUser);
        return ResponseEntity.ok(tmp);
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

    @GetMapping(value = "/user/{username}")
    private ResponseEntity<AppUser> getByUsername(@PathVariable("username") String username) {
        AppUser tmp = userService.findByUsername(username);
        return ResponseEntity.ok(tmp);
    }

    @PostMapping(value = "/user/password")
    private ResponseEntity<AppUser> changePassword(@RequestHeader(HttpHeaders.AUTHORIZATION) String token, @RequestBody ChangePasswordModel passwordModel) {
        AppUser appUser = userService.checkToken(token);
        if(appUser != null) {
            String sha256hex = Hashing.sha256()
                    .hashString(passwordModel.getPassword(), StandardCharsets.UTF_8)
                    .toString();
            appUser.setPassword(sha256hex);
            appUser.setUserToken("");
            userService.update(appUser);
            return ResponseEntity.ok(appUser);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

}
