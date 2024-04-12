package com.example.wrappedanytime.firebasewrapper;

import com.example.wrappedanytime.firebasewrapper.data.UserEntry;
import com.example.wrappedanytime.firebasewrapper.data.WrappedDataEntry;
import com.example.wrappedanytime.spotify.Datatypes.User;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

// Note: Should be declared a static instance
public class FirestoreDatabase {
    // TODO: need to verify connection is valid before making calls
    private static final FirebaseFirestore db = FirebaseFirestore.getInstance();
    public static final FirestoreCollection<UserEntry> USER_COLLECTION = new FirestoreCollection<>(db.collection("users"));
    public static final FirestoreCollection<WrappedDataEntry> WRAPPED_COLLECTION = new FirestoreCollection<>(db.collection("wraps"));
//    public static final FirestoreCollection<UserData> TEST_USER_COLLECTION = new FirestoreCollection<>(db.collection("test-users"));
//    public static final FirestoreCollection<WrappedData> TEST_WRAPPED_COLLECTION = new FirestoreCollection<>(db.collection("test-wraps"));
    public static void createUser (FirebaseUser fbUser, User spotifyUser){
        FirestoreDatabase.USER_COLLECTION.putItem(
                new UserEntry(
                        spotifyUser.getDisplayName(),
                        fbUser.getUid(),
                        spotifyUser.getId()
                )
        );
    }
}
