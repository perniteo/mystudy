package bitcamp.myapp.vo;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

public class Board implements Serializable {

  @Serial
  private static final long serialVersionUID = 1L;
  private String title;
  private String content;
  private String writer;
  private Date createDate;
  private int no;

  public static Board createFromCsv(String csv) {
    String[] values = csv.split(",");
    Board obj = new Board();
    obj.setTitle(values[0]);
    obj.setContent(values[1]);
    obj.setWriter(values[2]);
    obj.setCreatedDate(new Date(Long.parseLong(values[3])));
//    obj.setNo(values[4]);
    return obj;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getWriter() {
    return writer;
  }

  public void setWriter(String writer) {
    this.writer = writer;
  }

  public Date getCreatedDate() {
    return createDate;
  }

  public void setCreatedDate(Date createdDate) {
    this.createDate = createdDate;
  }

  public int getNo() {
    return no;
  }

  public void setNo(int no) {
    this.no = no;
  }

//  public String toCsvString() {
//    return String.format("%s,%s,%s,%d", this.title, this.content, this.writer,
//        this.createDate.getTime());
//  }
}
