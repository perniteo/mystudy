package bitcamp.myapp.vo;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class Member implements Serializable {

  @Serial
  private static final long serialVersionUID = 1L;
  private int no;
  private String email;
  private String name;
  private String password;
  private String photo;
  private Date joinDate;

}
