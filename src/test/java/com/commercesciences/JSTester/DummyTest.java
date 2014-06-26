package com.commercesciences.JSTester;

import com.commercesciences.JSTester.utils.DummyUtil;
import org.junit.Test;

import static org.junit.Assert.*;

public class DummyTest {

    @Test
    public void test() {
        assertEquals("dummy", DummyUtil.dummyMethod());
    }
}
