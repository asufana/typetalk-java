package com.github.asufana.typetalk.resources;

import lombok.*;

import org.joda.time.*;

@Value
@EqualsAndHashCode(callSuper = true)
public class Account extends AbstractResource {
    //see also: https://developer.nulab-inc.com/ja/docs/typetalk/api/1/get-profile
    
    //e.g. "id":7353
    private Integer id;
    //e.g. "name":"typetalk_api_test"
    private String name;
    //e.g. "fullName":"typetalk_api_test"
    private String fullname;
    //e.g. "suggestion":"typetalk_api_test"
    private String suggestion;
    //e.g. "imageUrl":"https://typetalk.in/accounts/7353/profile_image.png?t=1426921414901"
    private String imageUrl;
    //e.g. "createdAt":"2015-03-21T07:03:34Z"
    private String createdAt;
    //e.g. "updatedAt":"2015-03-21T07:05:14Z"
    private String updatedAt;
    
    public DateTime createdAt() {
        return toDateTime(createdAt);
    }
    
    public DateTime updatedAt() {
        return toDateTime(updatedAt);
    }
}
