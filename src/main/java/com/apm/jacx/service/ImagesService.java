package com.apm.jacx.service;

import com.apm.jacx.model.AppUser;
import com.apm.jacx.model.Image;
import com.apm.jacx.repository.ImagesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ImagesService {
    @Autowired
    private ImagesRepository imagesRepository;

    public Image create (Image image) {
        return imagesRepository.saveAndFlush(image);
    }

    public void delete (Image image) {
        imagesRepository.delete(image);
    }

    public Image getById (Long id) { return imagesRepository.getReferenceById(id); }

    public List<Image> findByOwner(AppUser appUser) {
        return imagesRepository.findAllByAppUser(appUser);
    }
}
