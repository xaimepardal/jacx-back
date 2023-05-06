package com.apm.jacx.repository;

import com.apm.jacx.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImagesRepository extends JpaRepository<Image, Long> {
}
