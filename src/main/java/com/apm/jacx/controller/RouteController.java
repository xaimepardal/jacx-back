package com.apm.jacx.controller;

import com.apm.jacx.model.AppUser;
import com.apm.jacx.model.Image;
import com.apm.jacx.model.Playlist;
import com.apm.jacx.model.Route;
import com.apm.jacx.model.dtos.RouteImage;
import com.apm.jacx.model.dtos.RouteModel;
import com.apm.jacx.model.dtos.RoutePlaylist;
import com.apm.jacx.model.dtos.RouteUser;
import com.apm.jacx.service.AppUserService;
import com.apm.jacx.service.ImagesService;
import com.apm.jacx.service.PlaylistService;
import com.apm.jacx.service.RouteService;
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
public class RouteController {
    @Autowired
    private RouteService routeService;

    @Autowired
    private AppUserService userService;

    @Autowired
    private PlaylistService playlistService;

    @Autowired
    private ImagesService imagesService;

    @PostMapping(value = "/route")
    private ResponseEntity<Route> create(@RequestBody RouteModel routeModel, @RequestHeader(HttpHeaders.AUTHORIZATION) String token) throws URISyntaxException {
        AppUser appUser = userService.checkToken(token);
        if (appUser != null) {
            Route tmp = routeService.create(routeModel, appUser);
            return ResponseEntity.created(new URI("api/route/" + tmp.getId())).body(tmp);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

    }

    @PutMapping(value = "/route")
    private ResponseEntity<Route> update(@RequestBody RouteModel routeModel) {
        Route tmp = routeService.update(routeModel);
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

    @GetMapping(value = "/route/owner")
    private ResponseEntity<List<Route>> getByOwner(@RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        AppUser appUser = userService.checkToken(token);
        if (appUser != null) {
            List<Route> tmp = routeService.findByOwner(appUser);
            return ResponseEntity.ok(tmp);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @GetMapping(value = "/route/{name}")
    private ResponseEntity<Route> getByName(@PathVariable("name") String name) {
        return ResponseEntity.ok(routeService.findByName(name));
    }

    @PostMapping(value = "/route/playlist")
    private ResponseEntity<Route> addPlaylistToRoute(@RequestHeader(HttpHeaders.AUTHORIZATION) String token, @RequestBody RoutePlaylist routePlaylist) {
        AppUser appUser = userService.checkToken(token);
        if (appUser != null) {
            Route route = routeService.findByName(routePlaylist.getRouteName());
            Playlist playlist = playlistService.getBySpotifyId(routePlaylist.getSpotifyID());
            if (playlist == null) {
                Playlist playlistNew = new Playlist();
                playlistNew.setSpotifyId(routePlaylist.getSpotifyID());
                playlist = playlistService.create(playlistNew);
            }
            List<Playlist> playlistsList = route.getPlaylists();
            playlistsList.add(playlist);
            route.setPlaylists(playlistsList);
            route = routeService.update(route);
            return ResponseEntity.ok(route);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @DeleteMapping(value = "/route/playlist")
    private ResponseEntity<Route> deletePlaylistToRoute(@RequestHeader(HttpHeaders.AUTHORIZATION) String token, @RequestBody RoutePlaylist routePlaylist) {
        AppUser appUser = userService.checkToken(token);
        if (appUser != null) {
            Route route = routeService.findByName(routePlaylist.getRouteName());
            Playlist playlist = playlistService.getBySpotifyId(routePlaylist.getSpotifyID());
            if (playlist == null || route == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
            route.getPlaylists().remove(playlist);
            playlistService.delete(playlist);
            route = routeService.update(route);
            return ResponseEntity.ok(route);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping(value = "/route/image")
    private ResponseEntity<Route> addImageToRoute(@RequestHeader(HttpHeaders.AUTHORIZATION) String token, @RequestBody RouteImage routeImage) {
        AppUser appUser = userService.checkToken(token);
        if (appUser != null) {
            Route route = routeService.findByName(routeImage.getRouteName());
            Image image = imagesService.getById(routeImage.getImageId());

            List<Image> imageList = route.getImages();
            imageList.add(image);
            route.setImages(imageList);
            route = routeService.update(route);
            return ResponseEntity.ok(route);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @DeleteMapping(value = "/route/image")
    private ResponseEntity<Route> deleteImageToRoute(@RequestHeader(HttpHeaders.AUTHORIZATION) String token, @RequestBody RouteImage routeImage) {
        AppUser appUser = userService.checkToken(token);
        if (appUser != null) {
            Route route = routeService.findByName(routeImage.getRouteName());
            Image image = imagesService.getById(routeImage.getImageId());

            List<Image> imageList = route.getImages();
            imageList.remove(image);
            route.setImages(imageList);
            route = routeService.update(route);
            return ResponseEntity.ok(route);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping(value = "/route/user")
    private ResponseEntity<Route> addUserToRoute(@RequestHeader(HttpHeaders.AUTHORIZATION) String token, @RequestBody RouteUser routeUser) {
        AppUser appUser = userService.checkToken(token);
        if (appUser != null) {
            Route route = routeService.findByName(routeUser.getRouteName());
            AppUser appUserToAdd = userService.findByUsername(routeUser.getUsername());

            List<AppUser> appUserList = route.getUsers();
            appUserList.add(appUserToAdd);
            route.setUsers(appUserList);
            route = routeService.update(route);
            return ResponseEntity.ok(route);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @DeleteMapping(value = "/route/user")
    private ResponseEntity<Route> deleteUserToRoute(@RequestHeader(HttpHeaders.AUTHORIZATION) String token, @RequestBody RouteUser routeUser) {
        AppUser appUser = userService.checkToken(token);
        if (appUser != null) {
            Route route = routeService.findByName(routeUser.getRouteName());
            AppUser appUserToAdd = userService.findByUsername(routeUser.getUsername());

            List<AppUser> appUserList = route.getUsers();
            appUserList.remove(appUserToAdd);
            route.setUsers(appUserList);
            route = routeService.update(route);
            return ResponseEntity.ok(route);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
