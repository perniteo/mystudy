package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class synchronizedPractice {

  public static void main(String[] args) throws URISyntaxException, IOException {

    URL url = new URI("https://trace.cjlogistics.com/web/detail.jsp?slipno=577453451342").toURL();

    InputStreamReader inputStreamReader = new InputStreamReader(url.openStream());
    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

    String str1;
    while ((str1 = bufferedReader.readLine()) != null) {
      System.out.println(str1);
    }

    // hash collection 순서 보장 안 함, 중복 없음(hashCode 비교 후 equals 값 비교)
    // tree collection 순서 보장 안 함, 이진 트리(레드 블랙)을 사용하기 때문에 생성자에 정렬이 구현 돼 있음
    // 재정의를 통해 역순으로 가능
    // map collection (k, v) 구조 k 부분은 중복 없음(set)
    Comparator<Integer> comparator = (o1, o2) -> Integer.compare(o2, o1);
    System.out.println(comparator.compare(3, 4));
    System.out.println(Integer.compare(3, 4));
    System.out.println("Fucker".compareTo("Hello"));
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
    // 순서 보장 가능
//    Map<Object, String> kv = new LinkedHashMap<>();
    kv.put("a", "1");
    kv.put(1, "s");
    kv.put(2, "e");
    kv.put(3, "x");
    kv.put("b", "2");
    kv.forEach((Object, String) -> System.out.printf("%s%s \t", Object, String));
    System.out.println();

    Set<String> hashSet = new HashSet<>();
    hashSet.add("Apple");
    hashSet.add("Banana");
    hashSet.add("Orange");
    hashSet.add(null);

    System.out.println("set size : " + hashSet.size());

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
