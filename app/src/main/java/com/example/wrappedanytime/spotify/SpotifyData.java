package com.example.wrappedanytime.spotify;

import static com.example.wrappedanytime.spotify.Authentication.mAccessToken;
import static com.example.wrappedanytime.spotify.Authentication.mOkHttpClient;

import android.app.Activity;
import android.util.Log;
import android.view.animation.AccelerateInterpolator;
import android.widget.Toast;

import com.example.wrappedanytime.spotify.Datatypes.Album;
import com.example.wrappedanytime.spotify.Datatypes.Artist;
import com.example.wrappedanytime.spotify.Datatypes.Image;
import com.example.wrappedanytime.spotify.Datatypes.Track;
import com.example.wrappedanytime.spotify.Datatypes.User;
import com.example.wrappedanytime.spotify.Datatypes.UserData;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class SpotifyData {
    private Activity activity;
    public SpotifyData(Activity activity) {
        this.activity = activity;
    }
    private class DataGetter implements Runnable {
        private String value;
        private Request request;
        private Call call;
        private OkHttpClient client;
        DataGetter(OkHttpClient client, Request request) {
            Log.d("myLog", "thread created");
            this.client = client;
            this.request = request;
        }
        @Override
        public void run() {
            Authentication.cancelCall();
            call = client.newCall(request);
            Response response;
            try {
                response = call.execute();
                value = response.body().string();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        public String getValue() {
            return value;
        }
    }
    public String retJSON(Request request) {
        DataGetter dg = new DataGetter(Authentication.mOkHttpClient, request);
        Thread thread =new Thread(dg);
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return dg.getValue();
    }
    public User getUser() {
        final Request request = new Request.Builder()
                .url("https://api.spotify.com/v1/me")
                .addHeader("Authorization", "Bearer " + mAccessToken)
                .build();
        return new User(retJSON(request));
    }

    public Track getTrack(String trackID) {
        final Request request = new Request.Builder()
            .url("https://api.spotify.com/v1/tracks/" + trackID)
            .addHeader("Authorization", "Bearer " + mAccessToken)
            .build();
        return new Track(retJSON(request));
    }
    public Album getAlbum(String albumID) {
        final Request request = new Request.Builder()
                .url("https://api.spotify.com/v1/albums/" + albumID)
                .addHeader("Authorization", "Bearer " + mAccessToken)
                .build();
        return new Album(retJSON(request));
    }
    public Artist getArtist(String id) {
        final Request request = new Request.Builder()
                .url("https://api.spotify.com/v1/artists/" + id)
                .addHeader("Authorization", "Bearer " + mAccessToken)
                .build();
        return new Artist(retJSON(request));
    }

    public UserData getUserData(UserData.TimeRange timeRange, int childCount) {
        FirebaseDatabase mFirebaseDatabase;
        FirebaseAuth mAuth;
        FirebaseAuth.AuthStateListener mAuthListener;
        DatabaseReference myRef;
        String userID;
        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference();
        FirebaseUser user = mAuth.getCurrentUser();
        userID = user.getUid();
        Date curDate = new Date(System.currentTimeMillis());
        Map<UserData.TimeRange, String> timeRangeMap = new HashMap<>();
        timeRangeMap.put(UserData.TimeRange.SHORT, "short_term");
        timeRangeMap.put(UserData.TimeRange.MEDIUM, "medium_term");
        timeRangeMap.put(UserData.TimeRange.LONG, "long_term");
        Request request = new Request.Builder()
            .url("https://api.spotify.com/v1/me/top/artists?time_range=" + timeRangeMap.get(timeRange))
            .addHeader("Authorization", "Bearer " + mAccessToken)
            .build();
        String artistRet = retJSON(request);
        request = new Request.Builder()
                .url("https://api.spotify.com/v1/me/top/tracks?time_range=" + timeRangeMap.get(timeRange))
                .addHeader("Authorization", "Bearer " + mAccessToken)
                .build();
        String trackRet = retJSON(request);
        UserData ret = new UserData(artistRet, trackRet);
        ret.setGenDate(curDate);
        ret.setTr(timeRange);
        String wrapNum = "Wrap " + childCount;
        myRef.child("users").child(userID).child("wraps").child(wrapNum).child("artistJSON").setValue(artistRet);
        myRef.child("users").child(userID).child("wraps").child(wrapNum).child("trackJSON").setValue(trackRet);
        myRef.child("users").child(userID).child("wraps").child(wrapNum).child("genDate").setValue(curDate);
        myRef.child("users").child(userID).child("wraps").child(wrapNum).child("tr").setValue(timeRange);


        return ret;
    }



}
