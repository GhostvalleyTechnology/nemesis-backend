package com.quellkunst.nemesis.security;

import java.util.function.Supplier;

public final class ExceptionSupplier<T extends Exception> implements Supplier<T>
{
  private final T exception;

  private ExceptionSupplier(T exception)
  {
    this.exception = exception;
  }

  public static <T extends Exception> ExceptionSupplier<T> theException(T exception)
  {
    return new ExceptionSupplier<>(exception);
  }

  @Override
  public T get()
  {
    return exception;
  }
}
