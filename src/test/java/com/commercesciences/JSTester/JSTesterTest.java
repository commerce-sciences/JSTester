package com.commercesciences.JSTester;

import org.junit.*;

import java.util.ArrayList;
import java.util.List;

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

    @Test
    public void testHasFunctionCallWithOneParamString() throws Exception {
        tester.setCodeBootstrap("function findMeWithParams(param1, param2){return param1+param2;}");
        String code = "findMeWithParams('a')";
        assertTrue(tester.code(code).hasFunctionCall("findMeWithParams", "a"));
    }

    @Test
    public void testNegativeHasFunctionCallWithOneParamString() throws Exception {
        tester.setCodeBootstrap("function findMeWithParams(param1, param2){return param1+param2;}");
        String code = "findMeWithParams('a')";
        assertFalse(tester.code(code).hasFunctionCall("findMeWithParams", "b"));
    }

    @Test
    public void testHasFunctionCallWithTwoParamsString() throws Exception {
        tester.setCodeBootstrap("function findMeWithParams(param1, param2){return param1+param2;}");
        String code = "findMeWithParams('a', 'b')";
        assertTrue(tester.code(code).hasFunctionCall("findMeWithParams", "a", "b"));
    }

    @Test
    public void testNegativeHasFunctionCallWithTwoParamsString() throws Exception {
        tester.setCodeBootstrap("function findMeWithParams(param1, param2){return param1+param2;}");
        String code = "findMeWithParams('a', 'b')";
        assertFalse(tester.code(code).hasFunctionCall("findMeWithParams", "b", "a"));
    }

    @Test
    public void testHasFunctionCallWithMixedParams() throws Exception {
        tester.setCodeBootstrap("function findMeWithParams(param1, param2){return param1+param2;}");
        String code = "findMeWithParams('a', true)";
        List<Object> params = new ArrayList<>();
        params.add("a");
        params.add(true);
        assertTrue(tester.code(code).hasFunctionCall("findMeWithParams", params));
    }

    @Test
    public void testNegativeHasFunctionCallWithMixedParams() throws Exception {
        tester.setCodeBootstrap("function findMeWithParams(param1, param2){return param1+param2;}");
        String code = "findMeWithParams('a', true)";
        List<Object> params = new ArrayList<>();
        params.add("a");
        params.add(false);
        assertFalse(tester.code(code).hasFunctionCall("findMeWithParams", params));
    }

    @Test
    public void testNegativeHasFunctionCallWithMixedParams2() throws Exception {
        tester.setCodeBootstrap("function findMeWithParams(param1, param2){return param1+param2;}");
        String code = "findMeWithParams('a', true)";
        List<Object> params = new ArrayList<>();
        params.add("b");
        params.add(true);
        assertFalse(tester.code(code).hasFunctionCall("findMeWithParams", params));
    }
}
