package com.apm.jacx.controller;

import com.apm.jacx.model.dtos.AddFriendByUsername;
import com.apm.jacx.model.AppUser;
import com.apm.jacx.model.dtos.Friend;
import com.apm.jacx.service.AppUserService;
import com.apm.jacx.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class FriendsController {

    @Autowired
    private FriendService friendService;
    @Autowired
    private AppUserService userService;


    @PostMapping("/friend")
    private ResponseEntity<List<Friend>> addFriendByUser(@RequestHeader(HttpHeaders.AUTHORIZATION) String token, @RequestBody AddFriendByUsername addFriendByUsername) {
        AppUser appUser = userService.checkToken(token);
        if (appUser != null) {
            List<Friend> tmp = friendService.addFriendByUser(appUser.getUsername(), addFriendByUsername.getUsername());
            return ResponseEntity.ok(tmp);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @GetMapping("/friends")
    private ResponseEntity<List<Friend>> getAllFriends(@RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        AppUser appUser = userService.checkToken(token);
        if (appUser != null) {
            List<Friend> tmp = friendService.getAllFriends(appUser.getUsername());
            return ResponseEntity.ok(tmp);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @DeleteMapping("/friend")
    private ResponseEntity<Void> deleteFriend(@RequestHeader(HttpHeaders.AUTHORIZATION) String token, @RequestBody AddFriendByUsername addFriendByUsername) {
        AppUser appUser = userService.checkToken(token);
        if (appUser != null) {
            friendService.deleteFriend(appUser.getUsername(), addFriendByUsername.getUsername());
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
