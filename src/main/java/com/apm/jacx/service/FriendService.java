package com.apm.jacx.service;

import com.apm.jacx.model.AppUser;
import com.apm.jacx.model.Friend;
import com.apm.jacx.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FriendService {

    @Autowired
    private AppUserRepository userRepository;

    public List<Friend> addFriendByUser(String username, String usernameFriend) {
        AppUser user = userRepository.findByUsername(username);
        AppUser friend = userRepository.findByUsername(usernameFriend);

        if (user == null || friend == null) {
            throw new RuntimeException("amigo no encontrado");
        } else {
            user.getFriends().add(friend);
            userRepository.save(user);

            List<Friend> listFriends = new ArrayList<>();
            for (AppUser appUser : user.getFriends()) {
                Friend tmp = new Friend(appUser.getUsername());
                listFriends.add(tmp);
            }
            return listFriends;

        }

    }

    public List<Friend> getAllFriends(String username) {
        AppUser user = userRepository.findByUsername(username);
        if (user == null) {
            throw new RuntimeException("Usuario non encontrado!");
        } else {
            List<Friend> listFriends = new ArrayList<>();
            for (AppUser appUser : user.getFriends()) {
                Friend tmp = new Friend(appUser.getUsername());
                listFriends.add(tmp);
            }
            return listFriends;
        }
    }

    public void deleteFriend(String username, String friendUsername) {
        AppUser user = userRepository.findByUsername(username);
        AppUser friend = userRepository.findByUsername(friendUsername);

        if (user == null || friend == null) {
            throw new RuntimeException("amigo no encontrado");
        } else {
            user.getFriends().remove(friend);
            userRepository.save(user);
        }
    }

}
