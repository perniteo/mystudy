package practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StreamTest {

  public static void main(String[] args) {
    List<Integer> numbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
    for (int i = 11; i < 5000000; i++) {
      numbers.add(i);
    }
    List<Integer> arr = new ArrayList<>(5000000);
    long start = System.currentTimeMillis();
    numbers.parallelStream().map(x -> x * 2).forEach(arr::add);
    System.out.println(System.currentTimeMillis() - start);

    // 순차 스트림
    // 병렬 스트림
    // numbers.parallelStream().forEach(System.out::println);
  }

}
