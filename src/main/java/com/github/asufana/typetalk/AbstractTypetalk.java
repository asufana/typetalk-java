package com.github.asufana.typetalk;

import java.io.*;

import com.github.asufana.typetalk.exceptions.*;
import com.google.gson.*;
import com.google.gson.reflect.*;
import com.squareup.okhttp.*;

public class AbstractTypetalk {
    
    protected <R> R execute(final Request request,
                            final ResponseCallback<R> callback) {
        try {
            final Response response = new OkHttpClient().newCall(request)
                                                        .execute();
            if (response.code() != 200) {
                throw new TypetalkException(request, response);
            }
            return callback.apply(response);
        }
        catch (final IOException e) {
            throw new TypetalkException(e);
        }
    }
    
    protected <T> T convert(final Response response, final TypeToken<T> token) throws IOException {
        return new Gson().fromJson(response.body().string(), token.getType());
    }
    
}
