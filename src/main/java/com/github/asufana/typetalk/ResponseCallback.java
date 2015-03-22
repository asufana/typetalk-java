package com.github.asufana.typetalk;

import java.io.*;

import com.squareup.okhttp.*;

@FunctionalInterface
public interface ResponseCallback<R> {
    
    R apply(final Response response) throws IOException;
    
}
