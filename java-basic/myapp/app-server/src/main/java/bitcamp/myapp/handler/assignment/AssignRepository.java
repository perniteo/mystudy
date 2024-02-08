package bitcamp.myapp.handler.assignment;

import bitcamp.myapp.vo.Assignment;

public class AssignRepository {

  private Assignment[] arr = new Assignment[3];
  private int length = 0;

  public void add(Assignment assignment) {
    if (this.arr.length == this.length) {
      Assignment[] newArr = new Assignment[this.arr.length + (
          this.arr.length << 1)];
      System.arraycopy(this.arr, 0, newArr, 0, this.arr.length);
      this.arr = newArr;
    }
    this.arr[this.length++] = assignment;
  }

  public Assignment remove(int index) {
    if (index < 0 || index > this.arr.length) {
      return null;
    }

    Assignment deleted = this.arr[index];

    for (int i = index; i < (this.arr.length - 1); i++) {
      this.arr[i] = this.arr[i + 1];
    }
    this.arr[--this.length] = null;

    return deleted;
  }

  public Assignment[] toArray() {
    Assignment[] newArr = new Assignment[this.length];
    System.arraycopy(arr, 0, newArr, 0, this.length);
    return newArr;
  }

  public Assignment get(int index) {
    if (index < 0 || index >= this.arr.length) {
      return null;
    }
    return this.arr[index];
  }

  public Assignment set(int index, Assignment assignment) {
    if (index < 0 || index >= this.arr.length) {
      return null;
    }
    Assignment old = this.arr[index];
    this.arr[index] = assignment;
    return old;
  }
}
