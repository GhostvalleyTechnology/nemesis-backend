package com.quellkunst.nemesis.security;

import io.quarkus.security.UnauthorizedException;

import java.util.function.Supplier;

public final class ExceptionSupplier<T extends Exception> implements Supplier<T> {
    private final T exception;

    private ExceptionSupplier(T exception) {
        this.exception = exception;
    }

    public static <T extends Exception> ExceptionSupplier<T> theException(T exception) {
        return new ExceptionSupplier<>(exception);
    }

    public static ExceptionSupplier<UnauthorizedException> unauthorizedException(String msg) {
        return new ExceptionSupplier<>(new UnauthorizedException(msg));
    }

    @Override
    public T get() {
        return exception;
    }
}
