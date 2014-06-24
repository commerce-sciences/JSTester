package com.commercesciences.JSTester;

import org.junit.*;
import static org.junit.Assert.*;

public class JSTesterTest {

    private JSTester tester;

    @Before
    public void setup() {
        tester = new JSTester();
    }


    @Test
    public void testHasFunctionCallJustName() throws Exception {
        tester.setCodeBootstrap("function findme(){}");
        String code = "findme();";
        assertTrue(tester.code(code).hasFunctionCall("findme"));
    }

    @Test
    public void testNegativeHasFunctionCallJustName() throws Exception {
        tester.setCodeBootstrap("function findme(){} function dontfindme(){}");
        String code = "dontfindme();";
        assertFalse(tester.code(code).hasFunctionCall("findme"));
    }
}
