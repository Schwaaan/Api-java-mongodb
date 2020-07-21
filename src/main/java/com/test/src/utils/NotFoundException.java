package com.test.src.utils;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {


  public NotFoundException() {
  }

  public NotFoundException(String var1) {
    super(var1);
  }

  public NotFoundException(String var1, Throwable var2) {
    super(var1, var2);
  }

  public NotFoundException(Throwable var1) {
    super(var1);
  }


}
