package com.quellkunst.nemesis.security;

import io.quarkus.security.ForbiddenException;
import io.quarkus.security.UnauthorizedException;
import java.util.function.Supplier;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.ServiceUnavailableException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ExceptionSupplier {

  public static Supplier<ForbiddenException> forbiddenException(String msg) {
    return () -> new ForbiddenException(msg);
  }

  public static Supplier<NotFoundException> notFoundException(String msg) {
    return () -> new NotFoundException(msg);
  }

  public static Supplier<UnauthorizedException> unauthorizedException(String msg) {
    return () -> new UnauthorizedException(msg);
  }

  public static Supplier<ServiceUnavailableException> serviceUnavailableException(String msg) {
    return () -> new ServiceUnavailableException(msg);
  }
}
