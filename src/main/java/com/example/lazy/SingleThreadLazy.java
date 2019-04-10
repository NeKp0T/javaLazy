package com.example.lazy;

import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class SingleThreadLazy<T> implements Lazy<T> {
    private Supplier<T> supplier;
    private T result;

    public SingleThreadLazy(@NotNull Supplier<T> supplier) {
        this.supplier = supplier;
    }

    @Override
    public T get() {
        if (supplier != null) {
            result = supplier.get();
            supplier = null;
        }
        return result;
    }


}
