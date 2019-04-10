package com.example.lazy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SingleThreadLazyTest {

    @Test
    void getSameObject() {
        var s = "res";
        var lazy = LazyFactory.createSingleThreadLazy(() -> s);
        var result1 = lazy.get();
        var result2 = lazy.get();
        assertSame(result1, result2);
    }

    private static class Tester {
        int number;
    }

    @Test
    void getRunOnce() {
        var s = "res";
        var tester = new Tester();
        Lazy<String> lazy = LazyFactory.createSingleThreadLazy(() -> {
            tester.number++;
            return s;
        });
        var result1 = lazy.get();
        var result2 = lazy.get();
        assertEquals(1, tester.number);
    }
}