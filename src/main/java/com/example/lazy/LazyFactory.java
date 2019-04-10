package com.example.lazy;

import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class LazyFactory {
    public static <T> Lazy<T> createSingleThreadLazy(@NotNull Supplier<T> supplier) {
        return new SingleThreadLazy<>(supplier);
    }
    public static <T> Lazy<T> createMultiThreadLazy(Supplier<T> supplier) {
        return null;
    }
    public static <T> Lazy<T> createLockFreeLazy(Supplier<T> supplier) {
        return new LockFreeLazy<>(supplier);
    }
}
