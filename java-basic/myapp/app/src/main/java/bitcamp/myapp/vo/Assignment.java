package bitcamp.myapp.vo;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Date;
import lombok.Data;

@Data
public class Assignment implements Serializable {

  @Serial
  private static final long serialVersionUID = 1L;

  private int no;
  private String title;
  private String content;
  private Date deadline;

}
