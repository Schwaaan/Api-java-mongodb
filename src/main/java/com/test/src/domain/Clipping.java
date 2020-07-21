package com.test.src.domain;

import com.test.src.domain.model.BaseModel;
import java.util.Date;
import lombok.Data;

@Data
public class Clipping extends BaseModel {

  private Date clippingDate;
  private String clippingMatter;
  private ClassificationType classificationType;
  private Date classifiedDate;
  private boolean important;
  private boolean viewed;

}
