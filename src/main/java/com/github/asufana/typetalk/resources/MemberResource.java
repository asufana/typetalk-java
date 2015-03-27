package com.github.asufana.typetalk.resources;

import lombok.*;

@Value
public class MemberResource {
    //see also: https://developer.nulab-inc.com/ja/docs/typetalk/api/1/get-topic-members
    
    private AccountResource account;
    private Boolean online;
    
}
