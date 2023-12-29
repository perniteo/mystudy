package bitcamp.util;

//게시글 데이터를 보관


import java.util.Arrays;

public class ArrayList<E> extends AbstractList<E> {

  private Object[] arr = new Object[3];
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
    E deleted = (E) this.arr[index];

    System.arraycopy(this.arr, index + 1, this.arr, index, length - index + 1);
//    for (int i = index; i < (this.length - 1); i++) {
//      this.arr[i] = this.arr[i + 1];
//    }
    this.arr[--this.length] = null;

    return deleted;
  }

  public boolean remove(E value) {
    for (int i = 0; i < this.size; i++) {
      if (this.arr[i].equals(value)) {
        this.remove(i);
        return true;
      }
    }
    return false;
  }

  public Object[] toArray() {
//    Object[] newArr = new Object[this.length];
//    System.arraycopy(this.arr, 0, newArr, 0, this.length);
//    return newArr;
    return Arrays.copyOf(this.arr, this.length);
  }

  public E[] toArray(E[] arr) {
//    for (int i = 0; i < this.length; i++) {
//      arr[i] = (E) this.arr[i];
//    }
    if (arr.length >= this.length) {
      System.arraycopy(this.arr, 0, arr, 0, this.length);
      return arr;
    }
    return (E[]) Arrays.copyOf(this.arr, this.length, arr.getClass());
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

  @Override
  public Iterator<E> iterator() {
    return new Iterator<>() {
      int cursor;

      @Override
      public boolean hasNext() {
        return cursor >= 0 && cursor < ArrayList.this.size();
      }

      @Override
      public E next() {
        return ArrayList.this.get(cursor++);
      }
    };
  }

  //  @Override
//  public Iterator<E> iterator() {
//    return new ArrayListIterator<>(this);
//  }

//  @Override
//  public Iterator<E> iterator() {
//    return new IteratorImpl<>(this);
//  }
//
//  private static class IteratorImpl<E> implements Iterator<E> {
//
//    ArrayList<E> list;
//    int cursor;
//
//    public IteratorImpl(ArrayList<E> list) {
//      this.list = list;
//    }
//
//    @Override
//    public boolean hasNext() {
//      return cursor >= 0 && cursor < this.list.size();
//    }
//
//    @Override
//    public E next() {
//      return list.get(this.cursor++);
//    }

//  private class IteratorImpl<E> implements Iterator<E> {
//
//  }

}


