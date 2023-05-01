package com.apm.jacx.service;

import com.apm.jacx.model.User;
import com.apm.jacx.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User create (User user) {
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void delete (User user) {
        userRepository.delete(user);
    }

    public Optional<User> findById (Long id) {
        return userRepository.findById(id);
    }
}
