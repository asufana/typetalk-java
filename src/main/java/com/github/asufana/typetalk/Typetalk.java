package com.github.asufana.typetalk;

import java.util.*;

import org.apache.commons.lang3.*;

import com.github.asufana.typetalk.exceptions.*;
import com.github.asufana.typetalk.resources.*;
import com.github.asufana.typetalk.resources.AccountResource.Account;
import com.github.asufana.typetalk.utils.*;
import com.google.gson.reflect.*;
import com.squareup.okhttp.*;

//TDDO maven run
//http://www.mastertheboss.com/jboss-web/jbosswebserver/undertow-web-server-tutorial
//http://emamotor.blogspot.jp/2013/12/introduction-undertow.html
//mvn clean compile exec:java -Dexec.mainClass=org.emamotor.undertow.practice.HelloWorldServer

public class Typetalk extends AbstractTypetalk {
    private static final String BASE_URL = "https://typetalk.in";
    private static final String CONNECT_URL = BASE_URL + "/oauth2/access_token";
    
    private final String clientId;
    private final String clientSecret;
    private final String accessToken;
    
    public Typetalk(final String clientId, final String clientSecret) {
        Validator.on(clientId).notNull("ClientId");
        Validator.on(clientSecret).notNull("ClientSecret");
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        accessToken = connect();
    }
    
    public String accessToken() {
        if (StringUtils.isEmpty(accessToken)) {
            throw new TypetalkException("アクセストークンが取得されていません");
        }
        return accessToken;
    }
    
    private String connect() {
        final Request request = new Request.Builder().url(CONNECT_URL)
                                                     .post(connectParams())
                                                     .build();
        
        return execute(request,
                       response -> {
                           final Map<String, String> jsonMap = convert(response,
                                                                       new TypeToken<Map<String, String>>() {});
                           return jsonMap.get("access_token");
                       });
    }
    
    protected RequestBody connectParams() {
        return new FormEncodingBuilder().add("client_id", clientId)
                                        .add("client_secret", clientSecret)
                                        .add("grant_type", "client_credentials")
                                        .add("scope",
                                             "my, topic.read, topic.post, topic.write, topic.delete")
                                        .build();
    }
    
    public Account profile() {
        final Request request = createRequest(BASE_URL + "/api/v1/profile");
        final AccountResource resource = execute(request, response -> {
            return convert(response, new TypeToken<AccountResource>() {});
        });
        return resource.account();
    }
    
    public List<TopicResource> topics() {
        final Request request = createRequest(BASE_URL + "/api/v1/topics");
        final TopicListResource listResource = execute(request, response -> {
            return convert(response, new TypeToken<TopicListResource>() {});
        });
        return listResource.topics();
    }
    
    public List<TalkResource> talks(final Integer topicId) {
        if (topicId == null) {
            throw new TypetalkException("TopicId is null");
        }
        
        final Request request = createRequest(BASE_URL
                + "/api/v1/topics/"
                + topicId.toString()
                + "/talks");
        final TalkListResource listResource = execute(request, response -> {
            return convert(response, new TypeToken<TalkListResource>() {});
        });
        return listResource.talks();
    }
    
    private Request createRequest(final String url) {
        final String accessTokenHeader = "Bearer " + accessToken();
        return new Request.Builder().url(url)
                                    .addHeader("Authorization",
                                               accessTokenHeader)
                                    .build();
    }
    
}
