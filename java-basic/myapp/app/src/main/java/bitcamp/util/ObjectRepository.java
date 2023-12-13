package bitcamp.util;

//게시글 데이터를 보관


public class ObjectRepository<E> {

  private java.lang.Object[] arr = new java.lang.Object[3];
  private int length = 0;

  public void add(E object) {
    if (this.arr.length == this.length) {
      Object[] newArr = new Object[this.arr.length + (this.arr.length / 2)];
      System.arraycopy(this.arr, 0, newArr, 0, this.length);
      this.arr = newArr;
    }
    this.arr[this.length++] = object;
  }

  public E remove(int index) {
    if (index < 0 || index >= this.length) {
      return null;
    }
    Object deleted = this.arr[index];

    for (int i = index; i < (this.length - 1); i++) {
      this.arr[i] = this.arr[i + 1];
    }
    this.arr[--this.length] = null;

    return (E) deleted;
  }

  public Object[] toArray() {
    Object[] newArr = new Object[this.length];
    System.arraycopy(this.arr, 0, newArr, 0, this.length);
    return newArr;
  }

  public void toArray(E[] arr) {
    for (int i = 0; i < this.length; i++) {
      arr[i] = (E) this.arr[i];
    }
  }

  public E get(int index) {
    if (index < 0 || index >= this.length) {
      return null;
    }
    return (E) this.arr[index];
  }

  public E set(int index, E object) {
    if (index < 0 || index >= this.length) {
      return null;
    }
    Object old = this.arr[index];
    this.arr[index] = object;

    return (E) old;
  }

  public int size() {
    return this.length;
  }
}

