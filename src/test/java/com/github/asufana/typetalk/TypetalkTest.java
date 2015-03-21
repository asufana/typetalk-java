package com.github.asufana.typetalk;

import org.junit.*;

public class TypetalkTest {
    
    public static final String CLIENT_ID = "RW7Xi2PKyGMtzoOPIu3qxYe02gMcPfGQ";
    public static final String CLIENT_SECRET = "RDDGLi3kEBHv041MiotTbEQovHig8AL03eEgprhG8xQLPW6U9MxiLcahm2lPDlCB";
    
    @Test
    public void test() {
        final Typetalk typetalk = new Typetalk(CLIENT_ID, CLIENT_SECRET);
        typetalk.profile();
    }
    
}
