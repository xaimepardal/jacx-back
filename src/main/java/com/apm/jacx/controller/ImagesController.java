package com.apm.jacx.controller;

import com.apm.jacx.model.Image;
import com.apm.jacx.service.ImagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ImagesController {
    @Autowired
    private ImagesService imagesService;

    @PostMapping(value = "/image")
    private ResponseEntity<Image> create(@RequestBody Image image) throws URISyntaxException {
        Image tmp = imagesService.create(image);
        return ResponseEntity.created(new URI("api/image/" + tmp.getId())).body(tmp);
    }

    @PutMapping(value = "/image")
    private ResponseEntity<Image> update(@RequestBody Image image) {
        Image tmp = imagesService.update(image);
        return ResponseEntity.ok(tmp);
    }

    @GetMapping(value = "/images")
    private ResponseEntity<List<Image>> getAll() {
        List<Image> tmp = imagesService.getAll();
        return ResponseEntity.ok(tmp);
    }

    @DeleteMapping(value = "/image")
    private ResponseEntity<Void> delete(@RequestBody Image image) {
        imagesService.delete(image);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/image/{id}")
    private ResponseEntity<Optional<Image>> getByID(@PathVariable("id") Long id) {
        Optional<Image> tmp = imagesService.findById(id);
        return ResponseEntity.ok(tmp);
    }
}
