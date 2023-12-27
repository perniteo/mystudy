package bitcamp.util;

public class LinkedList<T> {

  public Node<T> last;
  public Node<T> first;
  private int size;

  public LinkedList() {
    this.first = null;
  }

  public Object[] toArray() {
    Object[] result = new Object[size];
    int idx = 0;
    for (Node<T> x = first; x.next != null; x = x.next) {
      result[idx++] = x.data;
    }
    return result;
  }

  public int size() {
    return size;
  }

  public T get(int idx) {
    checkIndexError(idx);

    int cursor = 0;
    Node<T> node = first;

    while (cursor++ < idx) {
      node = node.next;
    }
    return node.data;
  }

  public Node<T> getNode(int idx) {
    checkIndexError(idx);

    int cursor = 0;
    Node<T> node = first;

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

  public void set(int idx, T element) {
    checkIndexError(idx);

    int cursor = 0;
    Node<T> node = first;

    while (cursor++ < idx) {
      node = node.next;
    }
    node.data = element;
  }

  public void add(T data) {
    Node<T> newNode = new Node<>(data);
    if (first == null) {
      first = newNode;
    } else {
      Node<T> current = first;
      while (current.next != null) {
        current = current.next;
      }
      current.next = newNode;
    }
    last = newNode;
    size++;
  }

  public void add(int idx, T data) {
    if (idx < 0 || idx > size) {
      throw new IndexOutOfBoundsException("Wrong idx");
    }

    Node<T> newNode = new Node<>(data);

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
      Node<T> node = first;
      while (++cursor < idx) {
        node = node.next;
      }
      newNode.next = node.next;
      node.next = newNode;
    }
    size++;
  }

  public void remove(int idx) {
    checkIndexError(idx);

    if (size == 1) {
      first = last = null;

    } else if (idx == 0) {
      first = first.next;

    } else if (idx == (size - 1)) {
      Node<T> node = getNode(idx - 1);
      node.next = null;
      last = node;

    } else {
      Node<T> node = getNode(idx - 1);
      node.next = getNode(idx + 1);

    }
    size--;

  }

  public void printList() {
    Node<T> current = first;
    while (current != null) {
      System.out.print(current.data + " ");
      current = current.next;
    }
    System.out.println();
  }
}
