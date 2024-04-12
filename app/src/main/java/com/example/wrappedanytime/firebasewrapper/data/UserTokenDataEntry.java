package com.example.wrappedanytime.firebasewrapper.data;

import java.util.Map;
import java.util.UUID;

public class UserTokenDataEntry extends FirebaseEntry {

    private String id;
    private UUID userId;
//    private String token; ?

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }


    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public Map<String, Object> toMap() {
        return null;
    }
}
