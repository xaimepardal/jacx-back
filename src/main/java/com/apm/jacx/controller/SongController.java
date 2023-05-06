package com.apm.jacx.controller;

import com.apm.jacx.model.Playlist;
import com.apm.jacx.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class SongController {
    @Autowired
    private SongService songService;

    @PostMapping(value = "/playlist")
    private ResponseEntity<Playlist> create(@RequestBody Playlist playlist) throws URISyntaxException {
        Playlist tmp = songService.create(playlist);
        return ResponseEntity.created(new URI("api/playlist/" + tmp.getId())).body(tmp);
    }

    @PutMapping(value = "/playlist")
    private ResponseEntity<Playlist> update(@RequestBody Playlist playlist) {
        Playlist tmp = songService.create(playlist);
        return ResponseEntity.ok(tmp);
    }

    @GetMapping(value = "/songs")
    private ResponseEntity<List<Playlist>> getAll() {
        List<Playlist> tmp = songService.getAll();
        return ResponseEntity.ok(tmp);
    }

    @DeleteMapping(value = "/playlist")
    private ResponseEntity<Void> delete(@RequestBody Playlist playlist) {
        songService.delete(playlist);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/song/{id}")
    private ResponseEntity<Optional<Playlist>> getByID(@PathVariable("id") Long id) {
        Optional<Playlist> tmp = songService.findById(id);
        return ResponseEntity.ok(tmp);
    }
}
