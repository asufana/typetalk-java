package com.github.asufana.typetalk;

import java.io.*;
import java.util.*;

import org.apache.http.*;
import org.apache.http.client.*;
import org.apache.http.client.entity.*;
import org.apache.http.client.methods.*;
import org.apache.http.impl.client.*;
import org.apache.http.message.*;
import org.apache.http.util.*;

import com.google.gson.*;
import com.google.gson.reflect.*;

public class Typetalk {
    
    //TODO chenge HttpClient to okHttp
    
    final HttpClient client = HttpClientBuilder.create().build();
    private static final String BASE_URL = "https://typetalk.in";
    private final String clientId;
    private final String clientSecret;
    private final String accessToken;
    
    public Typetalk(final String clientId, final String clientSecret) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.accessToken = connect();
    }
    
    private String connect() {
        final List<NameValuePair> tokenParams = Arrays.asList(new BasicNameValuePair("client_id",
                                                                                     clientId),
                                                              new BasicNameValuePair("client_secret",
                                                                                     clientSecret),
                                                              new BasicNameValuePair("grant_type",
                                                                                     "client_credentials"),
                                                              new BasicNameValuePair("scope",
                                                                                     "my"));
        
        final HttpPost tokenPost = new HttpPost(BASE_URL
                + "/oauth2/access_token");
        
        try {
            tokenPost.setEntity(new UrlEncodedFormEntity(tokenParams));
            final HttpResponse response = client.execute(tokenPost);
            final Map<String, String> json = new Gson().fromJson(EntityUtils.toString(response.getEntity(),
                                                                                      "UTF-8"),
                                                                 new TypeToken<Map<String, String>>() {}.getType());
            return json.get("access_token");
        }
        catch (final IOException e) {
            throw new RuntimeException(e);
        }
    }
    
    public void profile() {
        final HttpGet request = new HttpGet(BASE_URL + "/api/v1/profile");
        request.addHeader("Authorization", "Bearer " + accessToken);
        HttpResponse profileResponse;
        try {
            profileResponse = client.execute(request);
            System.out.println(EntityUtils.toString(profileResponse.getEntity(),
                                                    "UTF-8"));
        }
        catch (final IOException e) {
            throw new RuntimeException(e);
        }
    }
}
