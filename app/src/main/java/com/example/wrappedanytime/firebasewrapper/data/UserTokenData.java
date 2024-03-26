package com.example.wrappedanytime.firebasewrapper.data;

import java.util.UUID;

public class UserTokenData {
    private UUID userId;
//    private String token;

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }
}
