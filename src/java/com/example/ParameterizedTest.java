package com.example;

import static org.junit.runners.Parameterized.Parameters;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class ParameterizedTest {

    static {
        System.out.println("=== CLASSLOADERS ===");
        System.out.println("SYSTEM:" + ClassLoader.getSystemClassLoader().toString());
        for (Class c : new Class[] {
                ParameterizedTest.class,
                Parameterized.class,
                RunWith.class,
                Test.class,
        }) {
            System.out.println(c.getCanonicalName() + ": " + c.getClassLoader().toString());
        }
        System.out.println("=== END CLASSLOADERS ===");
    }

    private boolean expected;

    public ParameterizedTest(String name, boolean expected) {
        this.expected =  expected;
    }

    @Parameters(name = "{0}")
    public static Object[][] getParameters() {
        return new Object[][] {
                {"variant1", true},
                {"variant2", false},
        };
    }

    @Test
    public void testEquality() {
        assertEquals(expected, expected);
    }

}
