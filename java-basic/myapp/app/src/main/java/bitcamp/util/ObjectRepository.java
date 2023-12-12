package bitcamp.util;

//게시글 데이터를 보관


public class ObjectRepository {

  private java.lang.Object[] arr = new java.lang.Object[3];
  private int length = 0;

  public void add(Object object) {
    if (this.arr.length == this.length) {
      Object[] newArr = new Object[this.arr.length + (this.arr.length / 2)];
      System.arraycopy(this.arr, 0, newArr, 0, this.length);
      this.arr = newArr;
    }
    this.arr[this.length++] = object;
  }

  public Object remove(int index) {
    if (index < 0 || index >= this.length) {
      return null;
    }
    Object deleted = this.arr[index];

    for (int i = index; i < (this.length - 1); i++) {
      this.arr[i] = this.arr[i + 1];
    }
    this.arr[--this.length] = null;

    return deleted;
  }

  public Object[] toArray() {
    Object[] newArr = new Object[this.length];
    System.arraycopy(this.arr, 0, newArr, 0, this.length);
    return newArr;
  }

  public Object get(int index) {
    if (index < 0 || index >= this.length) {
      return null;
    }
    return this.arr[index];
  }

  public Object set(int index, Object object) {
    if (index < 0 || index >= this.length) {
      return null;
    }
    Object old = this.arr[index];
    this.arr[index] = object;

    return old;
  }
}

