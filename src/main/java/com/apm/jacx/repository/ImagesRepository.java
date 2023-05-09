package com.apm.jacx.repository;

import com.apm.jacx.model.AppUser;
import com.apm.jacx.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImagesRepository extends JpaRepository<Image, Long> {
    List<Image> findAllByOwner(AppUser owner);
}
