package com.example.wrappedanytime.firebasewrapper;

import androidx.annotation.NonNull;

import com.example.wrappedanytime.firebasewrapper.data.UserData;
import com.example.wrappedanytime.firebasewrapper.data.WrappedData;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

// Note: Should be declared a static instance
public class FirestoreWrapper {
    // TODO: need to verify connection is valid before making calls
    private static final FirebaseFirestore db = FirebaseFirestore.getInstance();
    public static final FirestoreCollection<UserData> userCollection = new FirestoreCollection<>(db.collection("users"));
    public static final FirestoreCollection<WrappedData> wrappedCollection = new FirestoreCollection<>(db.collection("wraps"));

//    public FirestoreWrapper() {
//        db = FirebaseFirestore.getInstance();
//        userCollection = new FirestoreCollection<>(db.collection("users"));
//        wrappedCollection = new FirestoreCollection<>(db.collection("wraps"));
//    }
//
//    // Used only for testing
//    public FirestoreWrapper(String userCollectionName, String wrappedCollectionName) {
//        db = FirebaseFirestore.getInstance();
//        userCollection = new FirestoreCollection<>(db.collection(userCollectionName));
//        wrappedCollection = new FirestoreCollection<>(db.collection(userCollectionName));
//    }
}
