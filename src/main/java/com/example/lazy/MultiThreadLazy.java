package com.example.lazy;

import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class MultiThreadLazy<T> implements Lazy<T> {

    private volatile T result;
    private Supplier<T> supplier;

    MultiThreadLazy(@NotNull Supplier<T> supplier) {
        result = (T) new Object();
        this.supplier = supplier;
    }

    @Override
    public T get() {
        if (supplier != null) {
            synchronized (result) {
                if (supplier != null) {
                    supplier = null;
                    result = supplier.get();
                }
            }
        }
        return result;
    }
}
