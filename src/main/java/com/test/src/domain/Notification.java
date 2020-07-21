package com.test.src.domain;

import com.test.src.domain.model.BaseModel;
import java.util.Date;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Notification extends BaseModel {

  private String description;
  private Date created_at;
  private boolean viewed;

}
