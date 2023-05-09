package com.apm.jacx.service;

import com.apm.jacx.model.AppUser;
import com.apm.jacx.model.dtos.LoginModel;
import com.apm.jacx.model.dtos.LoginResponse;
import com.apm.jacx.repository.AppUserRepository;
import com.google.common.hash.Hashing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class AppUserService {

    @Autowired
    private AppUserRepository userRepository;

    public AppUser create(AppUser appUser) {
        System.out.println(appUser);
        String sha256hex = Hashing.sha256()
                .hashString(appUser.getPassword(), StandardCharsets.UTF_8)
                .toString();
        appUser.setPassword(sha256hex);

        return userRepository.save(appUser);
    }

    public List<AppUser> getAll() {
        return userRepository.findAll();
    }

    public void delete(AppUser appUser) {
        userRepository.delete(appUser);
    }

    public AppUser findById(Long id) {
        return userRepository.getReferenceById(id);
    }

    public AppUser update(AppUser appUser) {
        AppUser user = findById(appUser.getId());
        if (appUser.getUsername() != null) {
            user.setUsername(appUser.getUsername());
        }
        if (appUser.getLastName() != null) {
            user.setLastName(appUser.getLastName());
        }
        if (appUser.getFirstName() != null) {
            user.setFirstName(appUser.getFirstName());
        }
        if (appUser.getBirthday() != null) {
            user.setBirthday(appUser.getBirthday());
        }
        if (appUser.getEmail() != null) {
            user.setEmail(appUser.getEmail());
        }
        return userRepository.save(user);
    }

    public AppUser findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public LoginResponse login(LoginModel loginModel) throws AuthenticationException {
        String sha256hex = Hashing.sha256()
                .hashString(loginModel.getPassword(), StandardCharsets.UTF_8)
                .toString();

        AppUser appUser = findByUsername(loginModel.getUsername());
        if (appUser == null) {
            throw new RuntimeException("username not exist");
        }

        if (appUser.getPassword().equals(sha256hex)) {

            SecureRandom random = new SecureRandom();
            byte[] bytes = new byte[8];
            random.nextBytes(bytes);
            String token = Arrays.toString(bytes).replaceAll(", |\\[|\\]|\\-", "");

            AppUser tmp = findById(appUser.getId());
            tmp.setUserToken(token);
            update(tmp);
            return new LoginResponse(token);

        } else {
            throw new AuthenticationException();
        }

    }

    public AppUser checkToken(String token) {
        return userRepository.findByUserToken(token);
    }
}
