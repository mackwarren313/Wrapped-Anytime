package com.example.wrappedanytime.firebasewrapper;

import com.example.wrappedanytime.firebasewrapper.data.FirebaseItem;
import com.example.wrappedanytime.firebasewrapper.data.UserData;
import com.google.android.gms.tasks.Task;
import com.google.common.reflect.TypeToken;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

// abstract if there are some things that need to be hardcoded
public class FirestoreCollection<T extends FirebaseItem> {
    private final CollectionReference cr;
    private final Map<UUID, T> cachedItemData;

    public FirestoreCollection(CollectionReference cr) {
        this.cr = cr;
        this.cachedItemData = new HashMap<>();
    }

    public void putItem(T itemData) {
        cr.document(itemData.getId().toString()).set(itemData);
        cachedItemData.put(itemData.getId(), itemData);
    }

    public T getItem(UUID itemId){
        T itemData = null;

        // check cached users
        if (cachedItemData.containsKey(itemId)) {
            itemData = cachedItemData.get(itemId);
            
        // get user data from user collection
        } else {
            Task<DocumentSnapshot> getItemRequest = cr.document(itemId.toString()).get();
            while (!getItemRequest.isComplete()) {
                // TODO : add wait limit from task timestamp
                if(getItemRequest.isSuccessful()) {
                    TypeToken<T> typeToken = new TypeToken<T>(getClass()) {};
                    itemData = getItemRequest.getResult().toObject(
                            (Class<T>) typeToken.getRawType()
                    );
                }
                if(getItemRequest.isCanceled()){
                    /* handle failure */
                    // TODO
                    itemData = null;
                }
            }
        }
        return itemData;
    }

    public void removeItem(UUID id){
        // remove cached
        cachedItemData.remove(id);
        // remove from user collection
        // TODO: condense get document
        cr.document(id.toString()).delete();
        // TODO: remove token
    }

    public void updateUser(T itemData) {

        // user document has few fields. we can pass in full data
        // update method does not support pojos
        cr.document(
                itemData.getId().toString()
        ).update(
            itemData.toMap()
        );
    }
}

