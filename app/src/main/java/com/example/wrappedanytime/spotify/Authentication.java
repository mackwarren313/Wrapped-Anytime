package com.example.wrappedanytime.spotify;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import com.example.wrappedanytime.MainActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.spotify.sdk.android.auth.AuthorizationClient;
import com.spotify.sdk.android.auth.AuthorizationRequest;
import com.spotify.sdk.android.auth.AuthorizationResponse;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
public class Authentication {
    public static final String CLIENT_ID = "0c8bef22a41149e383a987e5bcb4e03a";
    public static final String REDIRECT_URI = "wrapped-anytime://auth";
    public static final int AUTH_TOKEN_REQUEST_CODE = 0;
    public static final int AUTH_CODE_REQUEST_CODE = 1;

    static final OkHttpClient mOkHttpClient = new OkHttpClient();
    static String mAccessToken;
    private static String mAccessCode;
    static Call mCall;

    public static void getToken(Activity activity) {
        final AuthorizationRequest request = getAuthenticationRequest(AuthorizationResponse.Type.TOKEN);
        AuthorizationClient.openLoginActivity(activity, AUTH_TOKEN_REQUEST_CODE, request);
    }
    public static void setToken(String token) {
        mAccessToken = token;
    }
    public static String getAccessToken() {
        return mAccessToken;
    }
    public static void storeAuth(int requestCode, int resultCode, Intent data) {
        final AuthorizationResponse response = AuthorizationClient.getResponse(resultCode, data);
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        // Check which request code is present (if any)
        if (AUTH_TOKEN_REQUEST_CODE == requestCode) {
            mAccessToken = response.getAccessToken();
            mDatabase.child("users").child(user.getUid()).child("accessToken").setValue(mAccessToken);
        } else if (AUTH_CODE_REQUEST_CODE == requestCode) {
            mAccessCode = response.getCode();
        }
    }
    private static AuthorizationRequest getAuthenticationRequest(AuthorizationResponse.Type type) {
        return new AuthorizationRequest.Builder(CLIENT_ID, type, getRedirectUri().toString())
                .setShowDialog(false)
                .setScopes(new String[] { "user-read-email", "user-top-read"}) // <--- Change the scope of your requested token here
                .setCampaign("your-campaign-token")
                .build();
    }
    private static Uri getRedirectUri() {
        return Uri.parse(REDIRECT_URI);
    }
    static void cancelCall() {
        if (mCall != null) {
            mCall.cancel();
        }
    }
    public static boolean testAuth(String token, Activity activity) {
        Request request = new Request.Builder()
                .url("https://api.spotify.com/v1/me")
                .addHeader("Authorization", "Bearer " + token)
                .build();
        String retVal = new SpotifyData(activity).retJSON(request);
        if(retVal.contains("error")) return false;
        return true;
    }
}
