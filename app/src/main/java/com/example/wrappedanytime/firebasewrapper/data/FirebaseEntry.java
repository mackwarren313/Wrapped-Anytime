package com.example.wrappedanytime.firebasewrapper.data;

import java.util.Map;
import java.util.UUID;

public abstract class FirebaseEntry {
    public abstract void setId(String id);
    public abstract String getId();

    public abstract Map<String, Object> toMap();
}
