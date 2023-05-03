package com.apm.jacx.service;

import com.apm.jacx.model.AppUser;
import com.apm.jacx.model.Images;
import com.apm.jacx.repository.AppUserRepository;
import com.apm.jacx.repository.ImagesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ImagesService {
    @Autowired
    private ImagesRepository imagesRepository;

    public Images create (Images images) {
        return imagesRepository.save(images);
    }

    public List<Images> getAll() {
        return imagesRepository.findAll();
    }

    public void delete (Images images) {
        imagesRepository.delete(images);
    }

    public Optional<Images> findById (Long id) {
        return imagesRepository.findById(id);
    }
}
