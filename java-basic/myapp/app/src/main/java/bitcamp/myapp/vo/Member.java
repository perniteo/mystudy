package bitcamp.myapp.vo;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

public class Member implements Serializable {

  @Serial
  private static final long serialVersionUID = 1L;
  private int No;
  private String email;
  private String name;
  private String password;
  private Date joinDate;

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Date getCreatedDate() {
    return joinDate;
  }

  public void setCreatedDate(Date createdDate) {
    this.joinDate = createdDate;
  }

  public int getNo() {
    return No;
  }

  public void setNo(int no) {
    No = no;
  }
  
}
