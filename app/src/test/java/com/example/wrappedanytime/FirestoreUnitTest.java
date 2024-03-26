package com.example.wrappedanytime;

import android.util.Log;

import com.example.wrappedanytime.firebasewrapper.FirestoreWrapper;
import com.example.wrappedanytime.firebasewrapper.data.UserData;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class FirestoreUnitTest {
    private UserData userData;
    private FirestoreWrapper fdb;

    // check if failure is returned. for now test will always pass. still is required
    @Test
    public void testAddUser (){
        this.fdb = new FirestoreWrapper("test-user-collection", null);
        this.userData = new UserData();
        this.userData.setName("coolUser");
        this.userData.setUserId(UUID.randomUUID());
        this.userData.setSpotifyId("imagine-this-is-an-id");

        fdb.putUser(userData);
        assert(true);
    }

    @Test
    public void testGetUser(){
        UserData userData = fdb.getUser(this.userData.getUserId());
        assert(userData.equals(this.userData));
    }

    @Test
    public void testDeleteUser(){
        fdb.removeUser(this.userData.getUserId());
        assert(fdb.getUser(this.userData.getUserId()) == null);
    }

}
