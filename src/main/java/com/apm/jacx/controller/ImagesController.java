package com.apm.jacx.controller;

import com.apm.jacx.model.Images;
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
    private ResponseEntity<Images> create(@RequestBody Images images) throws URISyntaxException {
        Images tmp = imagesService.create(images);
        return ResponseEntity.created(new URI("api/image/" + tmp.getId())).body(tmp);
    }

    @GetMapping(value = "/images")
    private ResponseEntity<List<Images>> getAll() {
        List<Images> tmp = imagesService.getAll();
        return ResponseEntity.ok(tmp);
    }

    @DeleteMapping(value = "/image")
    private ResponseEntity<Void> delete(@RequestBody Images images) {
        imagesService.delete(images);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/image/{id}")
    private ResponseEntity<Optional<Images>> getByID(@PathVariable("id") Long id) {
        Optional<Images> tmp = imagesService.findById(id);
        return ResponseEntity.ok(tmp);
    }
}
