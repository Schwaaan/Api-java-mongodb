package com.test.src.domain;

import com.test.src.domain.model.BaseModel;
import java.util.Date;
import lombok.Data;

@Data
public class Appointment extends BaseModel {

  private Date dueDate;
  private String description;
  private Date created_at;

}
