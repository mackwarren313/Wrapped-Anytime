package com.example.wrappedanytime;

import com.example.wrappedanytime.firebasewrapper.FirestoreDatabase;
import com.example.wrappedanytime.firebasewrapper.data.UserEntry;

public class FirestoreUnitTest {
    private UserEntry userData;
    private FirestoreDatabase fdb;

    // check if failure is returned. for now test will always pass. still is required
//    @Test
//    public void testAddUser (){
//        this.userData = new UserData();
//        this.userData.setName("coolUser");
//        this.userData.setId(UUID.randomUUID());
//        this.userData.setSpotifyId("imagine-this-is-an-id");
//
//        FirestoreDatabase.USER_COLLECTION.putItem(userData);
//        assert(true);
//    }
//
//    @Test
//    public void testGetUser(){
//        UserData userData = FirestoreDatabase.USER_COLLECTION.getItem(this.userData.getId());
//        assert(userData.equals(this.userData));
//    }
//
//    @Test
//    public void testDeleteUser(){
//        FirestoreDatabase.USER_COLLECTION.removeItem(this.userData.getId());
//        assert(FirestoreDatabase.USER_COLLECTION.getItem(this.userData.getId()) == null);
//    }

}
