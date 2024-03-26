package com.example.wrappedanytime.firebasewrapper;

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

// Note: Token is not cached to prevent bad actors from reading other user auth data
// Note: Should be declared a static instance
public class FirestoreWrapper {
    private final Map<UUID, UserData> cachedUserData;
    private final Map<UUID, WrappedData> cachedWrappedData;
    // TODO: need to verify connection is valid before making calls
    private FirebaseFirestore db;
    private final CollectionReference userCollection;

    public FirestoreWrapper() {
        db = FirebaseFirestore.getInstance();
        userCollection = db.collection("users");
        cachedUserData = new HashMap<>();
        cachedWrappedData = new HashMap<>();
    }

    // Used only for testing
    public FirestoreWrapper(String userCollectionName) {
        db = FirebaseFirestore.getInstance();
        userCollection = db.collection(userCollectionName);
        cachedUserData = new HashMap<>();
        cachedWrappedData = new HashMap<>();
    }

    public void putUser(UserData userData){
//        FirebaseUser user = FirebaseAuth;
        userCollection.document(userData.getUserId().toString()).set(userData);
    }

    public UserData getUser(UUID userId){
        UserData userData = null;

        // check cached users
        if (cachedUserData.containsKey(userId)) {
            userData = cachedUserData.get(userId);

        // get user data from user collection
        } else {
            Task<DocumentSnapshot> getUserRequest = userCollection.document(userId.toString()).get();
            while (!getUserRequest.isComplete()) {
                // TODO : add wait limit from task timestamp
                if(getUserRequest.isSuccessful()) {
                    userData = getUserRequest.getResult().toObject(UserData.class);
                }
                if(getUserRequest.isCanceled()){
                    /* handle failure */
                    // TODO
                    userData = null;
                }
            }
        }
        return userData;
    }

    public void removeUser(UUID userId){
        // remove cached
        cachedUserData.remove(userId);
        // remove from user collection
        // TODO: condense get document
        userCollection.document(userId.toString()).delete();
        // TODO: remove token
    }

    public void updateUser(UserData userData) {

        // user document has few fields. we can pass in full data
        // update method does not support pojos
        userCollection.document(userData.getUserId().toString()).update(
                new HashMap<String, Object>() {{
                    put("name", userData.getName());
                    put("spotifyId", userData.getSpotifyId());
                }}
        );
    }

//    public void putWrapped(WrappedData wrappedData) {
//
//    }
//
//    public WrappedData getWrapped(UUID wrappedId) {
//        return null;
//    }
//
//    public void removeWrapped(UUID wrappedId) {
//
//    }
//
//    public void updateWrapped(WrappedData wrappedData){
//
//    }

}
