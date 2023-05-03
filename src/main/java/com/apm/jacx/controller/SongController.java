package com.apm.jacx.controller;

import com.apm.jacx.model.Song;
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

    @PostMapping(value = "/song")
    private ResponseEntity<Song> create(@RequestBody Song song) throws URISyntaxException {
        Song tmp = songService.create(song);
        return ResponseEntity.created(new URI("api/song/" + tmp.getId())).body(tmp);
    }

    @GetMapping(value = "/songs")
    private ResponseEntity<List<Song>> getAll() {
        List<Song> tmp = songService.getAll();
        return ResponseEntity.ok(tmp);
    }

    @DeleteMapping(value = "/song")
    private ResponseEntity<Void> delete(@RequestBody Song song) {
        songService.delete(song);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/song/{id}")
    private ResponseEntity<Optional<Song>> getByID(@PathVariable("id") Long id) {
        Optional<Song> tmp = songService.findById(id);
        return ResponseEntity.ok(tmp);
    }
}
