package com.example.lazy;

import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Supplier;

public class LockFreeLazy<T> implements Lazy<T> {
    private AtomicReference<Supplier<T>> supplier;
    private T result;
    private volatile boolean update = false;

    public LockFreeLazy(Supplier<T> supplier) {
        this.supplier = new AtomicReference<>(supplier);
    }

    @Override
    public T get() {
        if (!update) {
            Supplier<T> sup = supplier.get();
            if (sup == null) {
                while (!update);
                return result;
            }
            T res = sup.get();
            if (supplier.compareAndSet(sup, null)) {
                result = res;
                update = true;
            }
        }
        return result;
    }
}
