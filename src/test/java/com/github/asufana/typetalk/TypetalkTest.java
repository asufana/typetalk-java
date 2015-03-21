package com.github.asufana.typetalk;

import org.junit.*;

import com.squareup.okhttp.*;

public class TypetalkTest {
    
    public static final String CLIENT_ID = "RW7Xi2PKyGMtzoOPIu3qxYe02gMcPfGQ";
    public static final String CLIENT_SECRET = "RDDGLi3kEBHv041MiotTbEQovHig8AL03eEgprhG8xQLPW6U9MxiLcahm2lPDlCB";
    
    @Test(expected = RuntimeException.class)
    public void testConnectionError() throws Exception {
        new InvalidConnectParamsTypetalk(CLIENT_ID, CLIENT_SECRET);
    }
    
    @Test
    public void test() {
        final Typetalk typetalk = new Typetalk(CLIENT_ID, CLIENT_SECRET);
        typetalk.profile();
    }
    
    static class InvalidConnectParamsTypetalk extends Typetalk {
        public InvalidConnectParamsTypetalk(final String clientId,
                final String clientSecret) {
            super(clientId, clientSecret);
        }
        
        @Override
        protected RequestBody connectParams() {
            return null;
        }
    }
    
}
