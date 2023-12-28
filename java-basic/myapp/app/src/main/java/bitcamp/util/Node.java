package bitcamp.util;

public class Node<E> {

  Node<E> prev;
  E data;
  Node<E> next;

  public Node(E data) {
    this.data = data;
    this.next = null;
  }
}
