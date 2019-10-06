package com.ximouzhao.xml.XmlUtil;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor

@XStreamAlias("pn")
public class PhoneNumber {

  @XStreamAlias("codealias")
  private int code;
  private String number;
  private BigDecimal b;
  private Timestamp t;
  // ... constructors and methods
}