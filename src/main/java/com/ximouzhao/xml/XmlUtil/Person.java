package com.ximouzhao.xml.XmlUtil;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@XStreamAlias("message")
public class Person {
  private String firstname;

  @XStreamAlias("lastnamealias")
  private String lastname;

  @XStreamAlias("testphone")
  private PhoneNumber phone;

  @XStreamOmitField
  private PhoneNumber fax;

  @XStreamAlias("List")
  @XStreamImplicit
  private List<PhoneNumber> phoneNumberList;
}