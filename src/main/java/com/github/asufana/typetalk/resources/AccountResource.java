package com.github.asufana.typetalk.resources;

import lombok.*;

import org.joda.time.*;

@Value
@EqualsAndHashCode(callSuper = true)
public class AccountResource extends AbstractResource {
    //see also: https://developer.nulab-inc.com/ja/docs/typetalk/api/1/get-profile
    
    private Integer id;
    private String name;
    private String fullname;
    private String suggestion;
    private String imageUrl;
    private String createdAt;
    private String updatedAt;
    
    public DateTime createdAt() {
        return toDateTime(createdAt);
    }
    
    public DateTime updatedAt() {
        return toDateTime(updatedAt);
    }
}
