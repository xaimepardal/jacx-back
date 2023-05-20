package com.apm.jacx.repository;

import com.apm.jacx.model.AppUser;
import com.apm.jacx.model.Route;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RouteRepository extends JpaRepository<Route, Long> {
    Route findByName(String name);
    List<Route> findAllByAppUser(AppUser owner);
}
