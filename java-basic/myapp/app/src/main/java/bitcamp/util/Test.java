package bitcamp.util;

public class Test {

  public static void main(String[] args) {
//    java.util.LinkedList<Object> linkedList1 = new java.util.LinkedList<>();
    LinkedList<Object> linkedList = new LinkedList<>();
    linkedList.add("apple");
    linkedList.add("banana");
    linkedList.add("cherry");
    linkedList.add(69);
    linkedList.add(4, 74);
    linkedList.add(0, "why");
    linkedList.add(2, "juice");
    linkedList.remove(0);

    linkedList.printList();
//    System.out.println(linkedList.toArray());
    System.out.println(linkedList.get(3));
    System.out.println(linkedList.size() >> 1);
    System.out.println(linkedList.first.data);
    System.out.println(linkedList.last.data);

    Object[] arr = linkedList.toArray();

//    System.out.println(linkedList.head.data);
//    if (linkedList.head.next.next.next.data instanceof String) {
//      System.out.println("String");
//    }
//    System.out.println(linkedList.head.data.getClass());
//    System.out.println(linkedList.head.next.data);
  }

}
