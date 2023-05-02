package com.apm.jacx.service;

import com.apm.jacx.model.AppUser;
import com.apm.jacx.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppUserService {

    @Autowired
    private UserRepository userRepository;

    public AppUser create (AppUser appUser) {
        return userRepository.save(appUser);
    }

    public List<AppUser> getAllUsers() {
        return userRepository.findAll();
    }

    public void delete (AppUser appUser) {
        userRepository.delete(appUser);
    }

    public Optional<AppUser> findById (Long id) {
        return userRepository.findById(id);
    }
}
