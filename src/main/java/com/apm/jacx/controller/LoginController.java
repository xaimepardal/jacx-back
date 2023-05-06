package com.apm.jacx.controller;

import com.apm.jacx.model.AppUser;
import com.apm.jacx.model.LoginModel;
import com.apm.jacx.model.LoginResponse;
import com.apm.jacx.repository.AppUserRepository;
import com.apm.jacx.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/api")
public class LoginController {

    @Autowired
    private AppUserService userService;

    @PostMapping(value = "/login")
    private ResponseEntity<LoginResponse> login(@RequestBody LoginModel loginModel) {
        try {
            LoginResponse tmp = userService.login(loginModel);
            return ResponseEntity.ok(tmp);
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

    }
    @PostMapping(value = "/logout")
    private ResponseEntity<Void> logout (@RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        AppUser appUser = userService.checkToken(token);
        if(appUser != null) {
            appUser.setUserToken("");
            userService.update(appUser);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
