package practice;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class synchronizedPractice {

  public static void main(String[] args) {
    TreeSet<Integer> treeSet = new TreeSet<>((o1, o2) -> Integer.compare(o2, o1));
    treeSet.add(87);
    treeSet.add(98);
    treeSet.add(75);
    treeSet.add(95);
    treeSet.add(80);
    for (Integer num : treeSet) {
      System.out.println(num);
      System.out.println(num.compareTo(87));
    }

    long timestamp = 1705565777543L;

    // 타임스탬프를 Date 객체로 변환
    Date date = new Date(timestamp);

    // 원하는 형식으로 날짜 문자열 생성
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
    String formattedDate = sdf.format(date);

    System.out.println("Timestamp: " + timestamp);
    System.out.println("Formatted Date: " + formattedDate);
    Map<Object, String> kv = new HashMap<>();
    kv.put("a", "1");
    kv.put(1, "s");
    kv.put(2, "e");
    kv.put(3, "x");
    kv.put("b", "2");
    kv.forEach((Object, String) -> System.out.printf("%s%s \t", Object, String));

    Set<String> hashSet = new HashSet<>();
    hashSet.add("Apple");
    hashSet.add("Banana");
    hashSet.add("Orange");

    for (String str : hashSet) {
      System.out.println(str);
    }

    List<Integer> vector = new ArrayList<>();
    new Thread(() -> {
      System.out.println("Run Thread");
      synchronized (vector) {
        for (int i = 0; i < 10; i++) {
          System.out.println(i);
          vector.add(i);
        }
      }
    }).start();
    new Thread(() -> {
      System.out.println("Run Thread");
      synchronized (vector) {
        for (int i = 0; i < 10; i++) {
          System.out.println(i);
          vector.add(i);
        }
      }
    }).start();
    new Thread(() -> System.out.println("hi")).start();
    System.out.println(vector.size());
    for (int i = 0; i < vector.size(); i++) {
      System.out.println(vector.get(i));
    }
    kv.forEach((Object, String) -> System.out.printf("%s%s \t", Object, String));
    kv.forEach((Object, String) -> System.out.printf("%s%s \t", Object, String));
    kv.forEach((Object, String) -> System.out.printf("%s%s \t", Object, String));
    kv.forEach((Object, String) -> System.out.printf("%s%s \t", Object, String));
    kv.forEach((Object, String) -> System.out.printf("%s%s \t", Object, String));

  }

}
