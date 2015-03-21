package com.github.asufana.typetalk;

import java.io.*;
import java.util.*;

import com.google.gson.*;
import com.google.gson.reflect.*;
import com.squareup.okhttp.*;

public class Typetalk {
    private final OkHttpClient client = new OkHttpClient();
    
    private static final String BASE_URL = "https://typetalk.in";
    private static final String CONNECT_URL = BASE_URL + "/oauth2/access_token";
    
    private final String clientId;
    private final String clientSecret;
    private final String accessToken;
    
    public Typetalk(final String clientId, final String clientSecret) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        accessToken = connect();
    }
    
    private String connect() {
        final Request request = new Request.Builder().url(CONNECT_URL)
                                                     .post(connectParams())
                                                     .build();
        try {
            final Response response = client.newCall(request).execute();
            if (response.code() != 200) {
                throw new RuntimeException(response.body().string());
            }
            final Map<String, String> json = new Gson().fromJson(response.body()
                                                                         .string(),
                                                                 new TypeToken<Map<String, String>>() {}.getType());
            return json.get("access_token");
        }
        catch (final IOException e) {
            throw new RuntimeException(e);
        }
    }
    
    protected RequestBody connectParams() {
        return new FormEncodingBuilder().add("client_id", clientId)
                                        .add("client_secret", clientSecret)
                                        .add("grant_type", "client_credentials")
                                        .add("scope", "my")
                                        .build();
    }
    
    public void profile() {
        final Request request = new Request.Builder().url(BASE_URL
                + "/api/v1/profile")
                                                     .addHeader("Authorization",
                                                                "Bearer "
                                                                        + accessToken)
                                                     .build();
        
        try {
            final Response response = client.newCall(request).execute();
            System.out.println(response.body().string());
        }
        catch (final IOException e) {
            throw new RuntimeException(e);
        }
    }
}
