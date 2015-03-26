package com.github.asufana.typetalk;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.*;

import org.junit.*;

import com.github.asufana.typetalk.exceptions.*;
import com.github.asufana.typetalk.resources.AccountResource.Account;
import com.github.asufana.typetalk.resources.*;
import com.github.asufana.typetalk.utils.Validator.ValidatorException;
import com.squareup.okhttp.*;

public class TypetalkTest {
    
    public static final String CLIENT_ID = "RW7Xi2PKyGMtzoOPIu3qxYe02gMcPfGQ";
    public static final String CLIENT_SECRET = "RDDGLi3kEBHv041MiotTbEQovHig8AL03eEgprhG8xQLPW6U9MxiLcahm2lPDlCB";
    private Typetalk typetalk;
    
    @Before
    public void before() {
        typetalk = new Typetalk(CLIENT_ID, CLIENT_SECRET);
        assertThat(typetalk.accessToken(), is(notNullValue()));
    }
    
    @Test
    public void testProfile() {
        final Account account = typetalk.profile();
        assertThat(account, is(notNullValue()));
        assertThat(account.createdAt(), is(notNullValue()));
        System.out.println("OBJECT:" + account);
    }
    
    @Test
    public void testTopicList() {
        final List<TopicResource> resouces = typetalk.topics();
        assertThat(resouces.size(), is(not(0)));
        System.out.println("OBJECT:" + resouces);
    }
    
    @Test
    public void testTalkList() {
        final Integer topicId = typetalk.topics().get(0).topic().id();
        assertThat(topicId, is(notNullValue()));
        
        final List<TalkResource> resouces = typetalk.talks(topicId);
        assertThat(resouces.size(), is(not(0)));
        System.out.println("OBJECT:" + resouces);
    }
    
    //------------------------
    
    @Test(expected = ValidatorException.class)
    public void testConstructorException() throws Exception {
        new Typetalk(null, null);
    }
    
    @Test(expected = TypetalkException.class)
    public void testConnectionError() throws Exception {
        new InvalidConnectParamsOne(CLIENT_ID, CLIENT_SECRET);
    }
    
    static class InvalidConnectParamsOne extends Typetalk {
        public InvalidConnectParamsOne(final String clientId,
                final String clientSecret) {
            super(clientId, clientSecret);
        }
        
        @Override
        protected RequestBody connectParams() {
            return null;
        }
    }
    
}
