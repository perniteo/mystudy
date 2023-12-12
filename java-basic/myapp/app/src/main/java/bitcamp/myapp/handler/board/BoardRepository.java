package bitcamp.myapp.handler.board;

//게시글 데이터를 보관

import bitcamp.myapp.vo.Board;

public class BoardRepository {

  private Board[] arr = new Board[3];
  private int length = 0;

  public void add(Board board) {
    if (this.arr.length == this.length) {
      Board[] newArr = new Board[this.arr.length + (this.arr.length / 2)];
      System.arraycopy(this.arr, 0, newArr, 0, this.length);
      this.arr = newArr;
    }
    this.arr[this.length++] = board;
  }

  public Board remove(int index) {
    if (index < 0 || index >= this.length) {
      return null;
    }
    Board deleted = this.arr[index];

    for (int i = index; i < (this.length - 1); i++) {
      this.arr[i] = this.arr[i + 1];
    }
    this.arr[--length] = null;

    return deleted;
  }

  public Board[] toArray() {
    Board[] newArr = new Board[this.length];
    System.arraycopy(this.arr, 0, newArr, 0, this.length);
    return newArr;
  }

  public Board get(int index) {
    if (index < 0 || index >= this.length) {
      return null;
    }
    return this.arr[index];
  }

  public Board set(int index, Board board) {
    if (index < 0 || index >= this.length) {
      return null;
    }
    Board old = this.arr[index];
    this.arr[index] = board;

    return old;
  }
}

