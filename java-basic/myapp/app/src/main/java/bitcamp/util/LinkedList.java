package bitcamp.util;

import java.util.Arrays;

public class LinkedList<E> extends AbstractList<E> {

  public Node<E> last;
  public Node<E> first;
  private int size;

  public LinkedList() {
    this.first = null;
  }

  public Object[] toArray() {
    Object[] result = new Object[size];
    int idx = 0;
    for (Node<E> x = first; x.next != null; x = x.next) {
      result[idx++] = x.data;
    }
    return result;
  }

  public int size() {
    return size;
  }

  public E[] toArray(final E[] arr) {
    E[] values = arr;
    if (values.length < size) {
      values = Arrays.copyOf(arr, size);
    }

    int i = 0;
    Node<E> node = first;

    while (node != null) {
      values[i++] = node.data;
      node = node.next;
    }

    return values;
  }

  public E get(int idx) {
    checkIndexError(idx);

    int cursor = 0;
    Node<E> node = first;

    while (cursor++ < idx) {
      node = node.next;
    }
    return node.data;
  }

  public Node<E> getNode(int idx) {
    checkIndexError(idx);

    int cursor = 0;
    Node<E> node = first;

    while (cursor++ < idx) {
      node = node.next;
    }
    return node;
  }

  private void checkIndexError(int idx) {
    if (idx < 0 | idx >= size) {
      throw new IndexOutOfBoundsException("Wrong index");
    }
  }

  public E set(int idx, E element) {
    checkIndexError(idx);

    int cursor = 0;
    Node<E> node = first;

    while (cursor++ < idx) {
      node = node.next;
    }
    E old = node.data;
    node.data = element;

    return old;
  }

  public void add(E data) {
    Node<E> newNode = new Node<>(data);
    if (first == null) {
      first = newNode;
    } else {
      Node<E> current = first;
      while (current.next != null) {
        current = current.next;
      }
      current.next = newNode;
    }
    last = newNode;
    size++;
  }

  public void add(int idx, E data) {
    if (idx < 0 || idx > size) {
      throw new IndexOutOfBoundsException("Wrong idx");
    }

    Node<E> newNode = new Node<>(data);

    if (first == null) {
      first = last = newNode;

    } else if (idx == 0) {
      newNode.next = first;
      first = newNode;

    } else if (idx == size) {
      last.next = newNode;
      last = newNode;

    } else {
//      Node<T> prev = getNode(idx - 1);
//      Node<T> next = getNode(idx);
//      prev.next = newNode;
//      newNode.next = next;
      int cursor = 0;
      Node<E> node = first;
      while (++cursor < idx) {
        node = node.next;
      }
      newNode.next = node.next;
      node.next = newNode;
    }
    size++;
  }

  public E remove(int idx) {
    checkIndexError(idx);
    Node<E> deleted = getNode(idx);

    if (size == 1) {
//      deleted = first;
      first = last = null;

    } else if (idx == 0) {
//      deleted = first;
      first = first.next;

    } else if (idx == (size - 1)) {
//      deleted = last;
      Node<E> node = getNode(idx - 1);
      node.next = null;
      last = node;

    } else {
      Node<E> node = getNode(idx - 1);
      node.next = getNode(idx + 1);

    }
    size--;

    return deleted.data;
  }

  // null 배제
  public boolean remove(E value) {

    Node<E> prevNode = null;
    Node<E> node = first;

    while (node != null) {
      if (node.data.equals(value)) {
        break;
      }
      prevNode = node;
      node = node.next;
    }

    if (node == null) {
      return false;
    }

    if (node == first) {
      first = first.next;
      if (first == null) {
        last = null;
      }

    } else {
      prevNode.next = node.next;
    }

    size--;
    return true;
  }


  public void printList() {
    Node<E> current = first;
    while (current != null) {
      System.out.print(current.data + " ");
      current = current.next;
    }
    System.out.println();
  }
}
