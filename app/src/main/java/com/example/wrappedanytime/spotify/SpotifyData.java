package com.example.wrappedanytime.spotify;

import static com.example.wrappedanytime.spotify.Authentication.mAccessToken;

import android.app.Activity;
import android.util.Log;
import android.view.animation.AccelerateInterpolator;
import android.widget.Toast;

import com.example.wrappedanytime.spotify.Datatypes.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

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
    public class DataGetter implements Runnable {
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
    public User getUser() {
        if (Authentication.mAccessToken == null) {
            Authentication.getToken(activity);
        }

        // Create a request to get the user profile
        final Request request = new Request.Builder()
                .url("https://api.spotify.com/v1/me")
                .addHeader("Authorization", "Bearer " + mAccessToken)
                .build();
        DataGetter dg = new DataGetter(Authentication.mOkHttpClient, request);
        Thread thread =new Thread(dg);
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        String userString = dg.getValue();
        Log.d("myLog", userString);


        return null;
    }
}
