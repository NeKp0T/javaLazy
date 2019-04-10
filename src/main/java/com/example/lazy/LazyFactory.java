package com.example.lazy;

import java.util.function.Supplier;

public class LazyFactory {
    public static <T> Lazy<T> createOneThreadLazy(Supplier<T> supplier) {

    }
    public static <T> Lazy<T> createMultiThreadLazy(Supplier<T> supplier) {
        if (supplier == null) {
            throw new IllegalArgumentException();
        }
        return new MultiThreadLazy<>(supplier);
    }
    public static <T> Lazy<T> createLockFreeLazy(Supplier<T> supplier) {

    }
}
