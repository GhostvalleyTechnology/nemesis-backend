package com.quellkunst.nemesis.security;

import io.quarkus.security.ForbiddenException;
import io.quarkus.security.UnauthorizedException;

import java.util.function.Supplier;

public final class ExceptionSupplier<T extends Exception> implements Supplier<T> {
  private final T exception;

  private ExceptionSupplier(T exception) {
    this.exception = exception;
  }

  public static ExceptionSupplier<ForbiddenException> forbiddenException(String msg) {
    return new ExceptionSupplier<>(new ForbiddenException(msg));
  }

  public static ExceptionSupplier<UnauthorizedException> unauthorizedException(String msg) {
    return new ExceptionSupplier<>(new UnauthorizedException(msg));
  }

  @Override
  public T get() {
    return exception;
  }
}
