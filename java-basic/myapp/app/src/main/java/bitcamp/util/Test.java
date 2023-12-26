package bitcamp.util;

public class Test {

  public static void main(String[] args) {
    LinkedList<Object> linkedList = new LinkedList<>();
    linkedList.add("apple");
    linkedList.add("banana");
    linkedList.add("cherry");
    linkedList.add(69);

    linkedList.printList();
    System.out.println(linkedList.head.data);
  }

}
