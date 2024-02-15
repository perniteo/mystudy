package bitcamp.myapp.vo;

public class AttachedFile {

  private int boardNo;
  private int no;
  private String filePath;

  public int getNo() {
    return no;
  }

  public void setNo(int no) {
    this.no = no;
  }

  public AttachedFile filePath(String filePath) {
    this.filePath = filePath;
    return this;
  }

  public String getFilePath() {
    return filePath;
  }

  public void setFilePath(String filePath) {
    this.filePath = filePath;
  }

  public int getBoardNo() {
    return boardNo;
  }

  public void setBoardNo(int boardNo) {
    this.boardNo = boardNo;
  }

  public AttachedFile boardNo(int boardNo) {
    this.boardNo = boardNo;
    return this;
  }
}
