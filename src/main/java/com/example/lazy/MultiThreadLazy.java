package com.example.lazy;

import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class MultiThreadLazy<T> implements Lazy<T> {

    private T result;
    private Supplier<T> supplier;
    boolean gotResult;

    MultiThreadLazy(@NotNull Supplier<T> supplier) throws IllegalArgumentException {
        result = (T) new Object();
        this.supplier = supplier; // TODO mb synchronized?
    }

    @Override
    public T get() {
        if (!gotResult) {
            synchronized (result) {
                if (!gotResult) {
                    result = supplier.get();
                    supplier = null;
                    gotResult = true;
                }
            }
        }
        return result;
    }
}
