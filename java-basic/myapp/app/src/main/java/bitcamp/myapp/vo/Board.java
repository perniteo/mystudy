package bitcamp.myapp.vo;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import lombok.Data;

@Data
public class Board implements Serializable {

  private static final long serialVersionUID = 1L;
  @Serial
  private int category;
  private String title;
  private String content;
  private Member writer;
  private Date createdDate;
  private int no;
  private List<AttachedFile> files;
  private int fileCount;
  
}
