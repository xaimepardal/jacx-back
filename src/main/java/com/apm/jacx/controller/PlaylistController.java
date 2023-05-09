package com.apm.jacx.controller;

import com.apm.jacx.model.AppUser;
import com.apm.jacx.model.Image;
import com.apm.jacx.model.Playlist;
import com.apm.jacx.service.AppUserService;
import com.apm.jacx.service.PlaylistService;
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
public class PlaylistController {
    @Autowired
    private PlaylistService playlistService;

    @Autowired
    private AppUserService userService;

    @PostMapping(value = "/playlist")
    private ResponseEntity<Playlist> create(@RequestBody Playlist playlist, @RequestHeader(HttpHeaders.AUTHORIZATION) String token) throws URISyntaxException {
        AppUser appUser = userService.checkToken(token);
        if (appUser != null) {
            playlist.setOwner(appUser);
            Playlist tmp = playlistService.create(playlist);
            return ResponseEntity.created(new URI("api/playlist/" + tmp.getId())).body(tmp);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @GetMapping(value = "/playlists")
    private ResponseEntity<List<Playlist>> getAll() {
        List<Playlist> tmp = playlistService.getAll();
        return ResponseEntity.ok(tmp);
    }

    @DeleteMapping(value = "/playlist")
    private ResponseEntity<Void> delete(@RequestBody Playlist playlist) {
        playlistService.delete(playlist);
        return ResponseEntity.ok().build();
    }
}
