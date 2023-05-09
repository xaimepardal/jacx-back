package com.apm.jacx.controller;

import com.apm.jacx.model.AppUser;
import com.apm.jacx.model.Image;
import com.apm.jacx.service.AppUserService;
import com.apm.jacx.service.ImagesService;
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
public class ImagesController {
    @Autowired
    private ImagesService imagesService;

    @Autowired
    private AppUserService userService;

    @PostMapping(value = "/image")
    private ResponseEntity<Image> create(@RequestBody Image image, @RequestHeader(HttpHeaders.AUTHORIZATION) String token) throws URISyntaxException {
        AppUser appUser = userService.checkToken(token);
        if (appUser != null) {
            image.setOwner(appUser);
            Image tmp = imagesService.create(image);
            return ResponseEntity.created(new URI("api/image/" + tmp.getId())).body(tmp);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @GetMapping(value = "/images")
    private ResponseEntity<List<Image>> getAllByOwner(@RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        AppUser appUser = userService.checkToken(token);
        if (appUser != null) {
            List<Image> tmp = imagesService.findByOwner(appUser);
            return ResponseEntity.ok(tmp);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @DeleteMapping(value = "/image")
    private ResponseEntity<Void> delete(@RequestBody Image image, @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        AppUser appUser = userService.checkToken(token);
        if (appUser != null) {
            imagesService.delete(image);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
