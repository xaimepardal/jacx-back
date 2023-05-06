package com.apm.jacx.model;

import lombok.Data;

@Data
public class Friend {
    private String usernameFriend;

    public Friend(String usernameFriend) {
        this.usernameFriend = usernameFriend;
    }
}
