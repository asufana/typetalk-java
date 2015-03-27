
[![Build Status](https://travis-ci.org/asufana/typetalk-java.svg?branch=master)](https://travis-ci.org/asufana/typetalk-java)

# typetalk-java

Typetalk API for Java8.


## How to use

```java

//Connect
Typetalk typetalk = new Typetalk(YOUR_CLIENT_ID, YOUR_CLIENT_SECRET);

//Profile
AccountResource account = typetalk.profile();

//Topic list
List<TopicResource> topis = typetalk.topics();

//Account list
List<AccountResource> accounts = typetalk.account();

//Talk list
Integer topicId = topics.get(0).topic.id();
List<TalkResource> talks = typetalk.talks(topicId);

//Topic Member list
List<MemberResource> members = typetalk.members(topicId);
```
