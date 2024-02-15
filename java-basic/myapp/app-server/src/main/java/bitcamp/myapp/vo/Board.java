package bitcamp.myapp.vo;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Board implements Serializable {

  @Serial
  private static final long serialVersionUID = 1L;
  private String title;
  private String content;
  private Member writer;
  private Date createdDate;
  private int no;
  private List<AttachedFile> files;
  private int fileCount;

  public List<AttachedFile> getFiles() {
    return files;
  }

  public void setFiles(List<AttachedFile> files) {
    this.files = files;
  }

  public int getFileCount() {
    return fileCount;
  }

  public void setFileCount(int fileCount) {
    this.fileCount = fileCount;
  }

  @Override
  public String toString() {
    return "Board{" +
        "no=" + no +
        ", title='" + title + '\'' +
        ", content='" + content + '\'' +
        ", writer='" + writer + '\'' +
        ", createdDate=" + createdDate +
        ", files=" + files +
        ", fileCount=" + fileCount +
        '}';
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

  public Member getWriter() {
    return writer;
  }

  public void setWriter(Member writer) {
    this.writer = writer;
  }

  public Date getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
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
