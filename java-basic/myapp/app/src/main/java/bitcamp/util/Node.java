package bitcamp.util;

public class Node<T> {

  Node<T> prev;
  T data;
  Node<T> next;

  public Node(T data) {
    this.data = data;
    this.next = null;
  }
}
