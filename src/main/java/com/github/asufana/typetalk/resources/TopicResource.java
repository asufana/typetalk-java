package com.github.asufana.typetalk.resources;

import lombok.*;

import org.joda.time.*;

@Value
public class TopicResource {
    //see also: https://developer.nulab-inc.com/ja/docs/typetalk/api/1/get-topics
    
    private Topic topic;
    private String favorite;
    private Unread unread;
    
    @Value
    @EqualsAndHashCode(callSuper = true)
    public static class Topic extends AbstractResource {
        private Integer id;
        private String name;
        private String suggestion;
        private String lastPostedAt;
        private String createdAt;
        private String updatedAt;
        
        public DateTime lastPostedAt() {
            return toDateTime(lastPostedAt);
        }
        
        public DateTime createdAt() {
            return toDateTime(createdAt);
        }
        
        public DateTime updatedAt() {
            return toDateTime(updatedAt);
        }
    }
    
    @Value
    public static class Unread {
        private Integer topicId;
        private Integer postId;
        private Integer count;
    }
}
