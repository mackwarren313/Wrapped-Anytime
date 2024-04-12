package com.example.wrappedanytime.firebasewrapper;

import com.example.wrappedanytime.firebasewrapper.data.UserData;
import com.example.wrappedanytime.firebasewrapper.data.WrappedData;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

// Note: Should be declared a static instance
public class FirestoreDatabase {
    // TODO: need to verify connection is valid before making calls
    private static final FirebaseFirestore db = FirebaseFirestore.getInstance();
    public static final FirestoreCollection<UserData> USER_COLLECTION = new FirestoreCollection<>(db.collection("users"));
    public static final FirestoreCollection<WrappedData> WRAPPED_COLLECTION = new FirestoreCollection<>(db.collection("wraps"));
//    public static final FirestoreCollection<UserData> TEST_USER_COLLECTION = new FirestoreCollection<>(db.collection("test-users"));
//    public static final FirestoreCollection<WrappedData> TEST_WRAPPED_COLLECTION = new FirestoreCollection<>(db.collection("test-wraps"));

    public static void createUserData(UserData userData){
        FirestoreDatabase.USER_COLLECTION.putItem(userData);
    }
    public static void deleteUserData(UserData userData){
        FirestoreDatabase.USER_COLLECTION.removeItem(userData.getId());
    }


}
