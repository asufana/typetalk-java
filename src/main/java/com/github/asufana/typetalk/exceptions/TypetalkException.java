package com.github.asufana.typetalk.exceptions;

import lombok.*;

import org.slf4j.*;

import com.squareup.okhttp.*;

@Getter
public class TypetalkException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(TypetalkException.class);
    
    private final Exception exception;
    private final String message;
    
    public TypetalkException(final Request request, final Response response) {
        this(null, String.format("%s : %s",
                                 response.message(),
                                 request.urlString()));
    }
    
    public TypetalkException(final String message) {
        this(null, message);
    }
    
    public TypetalkException(final Exception exception) {
        this(exception, String.format("%s: %s",
                                      exception.getClass().getSimpleName(),
                                      exception.getMessage()));
    }
    
    public TypetalkException(final Exception exception, final String message) {
        super();
        this.exception = exception;
        this.message = message;
        
        logger.error("TypetalkException: {}", message);
    }
}
