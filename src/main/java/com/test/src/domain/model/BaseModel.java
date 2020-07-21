package com.test.src.domain.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class BaseModel {

  @Id
  private String id;

  private boolean deleted;

}
