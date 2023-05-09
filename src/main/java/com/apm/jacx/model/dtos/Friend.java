package com.apm.jacx.model.dtos;

import lombok.Data;

@Data
public class Friend {
    private String usernameFriend;

    public Friend(String usernameFriend) {
        this.usernameFriend = usernameFriend;
    }
}
