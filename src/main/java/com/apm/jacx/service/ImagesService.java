package com.apm.jacx.service;

import com.apm.jacx.model.AppUser;
import com.apm.jacx.model.Image;
import com.apm.jacx.repository.ImagesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ImagesService {
    @Autowired
    private ImagesRepository imagesRepository;

    public Image create (Image image) {
        return imagesRepository.save(image);
    }

    public List<Image> getAll() {
        return imagesRepository.findAll();
    }

    public void delete (Image image) {
        imagesRepository.delete(image);
    }

    public Optional<Image> findById (Long id) {
        return imagesRepository.findById(id);
    }

    public Image update(Image image) { return imagesRepository.save(image); }
}
