package com.github.asufana.typetalk.resources;

import lombok.*;

import org.joda.time.*;

@Value
@EqualsAndHashCode(callSuper = true)
public class TalkResource extends AbstractResource {
    //see also: https://developer.nulab-inc.com/ja/docs/typetalk/api/1/get-talks
    
    private Integer id;
    private Integer topicId;
    private String name;
    private String suggestion;
    private String createdAt;
    private String updatedAt;
    
    public DateTime createdAt() {
        return toDateTime(createdAt);
    }
    
    public DateTime updatedAt() {
        return toDateTime(updatedAt);
    }
}
