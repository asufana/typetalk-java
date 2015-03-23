package com.github.asufana.typetalk;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.*;

import com.github.asufana.typetalk.exceptions.*;
import com.github.asufana.typetalk.resources.*;
import com.github.asufana.typetalk.utils.Validator.ValidatorException;
import com.squareup.okhttp.*;

public class TypetalkTest {
    
    public static final String CLIENT_ID = "RW7Xi2PKyGMtzoOPIu3qxYe02gMcPfGQ";
    public static final String CLIENT_SECRET = "RDDGLi3kEBHv041MiotTbEQovHig8AL03eEgprhG8xQLPW6U9MxiLcahm2lPDlCB";
    
    @Test
    public void test() {
        final Typetalk typetalk = new Typetalk(CLIENT_ID, CLIENT_SECRET);
        final Account account = typetalk.profile();
        assertThat(account, is(notNullValue()));
        assertThat(account.createdAt(), is(notNullValue()));
        System.out.println(account);
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
