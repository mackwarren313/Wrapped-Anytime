package com.example.wrappedanytime.spotify;

import static com.example.wrappedanytime.spotify.Authentication.mAccessToken;
import static com.example.wrappedanytime.spotify.Authentication.mOkHttpClient;

import android.app.Activity;
import android.util.Log;
import android.view.animation.AccelerateInterpolator;
import android.widget.Toast;

import com.example.wrappedanytime.spotify.Datatypes.User;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

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
        User ret = new User();
        final Request request = new Request.Builder()
                .url("https://api.spotify.com/v1/me")
                .addHeader("Authorization", "Bearer " + mAccessToken)
                .build();
        JsonElement jsonElement = JsonParser.parseString(retJSON(request));
        if (jsonElement.isJsonObject()) {
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            if (jsonObject.has("display_name")) {
                String name = jsonObject.get("display_name").getAsString();
                ret.setDisplayName(name);
            }
            if (jsonObject.has("id")) {
                String id = jsonObject.get("id").getAsString();
                ret.setId(id);
            }
            if (jsonObject.has("uri")) {
                String uri = jsonObject.get("uri").getAsString();
                ret.setUri(uri);
            }
            if (jsonObject.has("images")) {
                JsonArray jsonArray = jsonObject.getAsJsonArray("images");
                JsonObject largestImage = jsonArray.get(jsonArray.size()-1).getAsJsonObject();
                String imageUrl = largestImage.get("url").getAsString();
                int height = largestImage.get("height").getAsInt();
                int width = largestImage.get("width").getAsInt();
                ret.setPfp(new User.ProfilePic(imageUrl, height, width));
            }
            if (jsonObject.has("email")) {
                String email = jsonObject.get("email").getAsString();
                ret.setEmail(email);
            }
        }

        return ret;
    }
    private String retJSON(Request request) {
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
}
