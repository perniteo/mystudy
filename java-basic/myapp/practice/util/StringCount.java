package practice.util;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StringCount {

  public static void main(String[] args) {
    String str11 = "Fucker🍬🍬🍬🍬🍬🍬🍬";

    for (int i = 0; i < str11.length(); i++) {
      int cp = str11.codePointAt(i);
      String ch = String.valueOf(Character.toChars(cp));
      System.out.println(i + ch);
      if (Character.charCount(cp) == 2) {
        ++i;
      }
    }

    IntStream intStream = str11.codePoints();
    System.out.println(intStream);

    Map<String, Long> map = intStream.mapToObj(c -> String.valueOf(Character.toChars(c))).collect(
        Collectors.groupingBy(c -> c, Collectors.counting()));

    map.forEach((k, v) ->
        System.out.println(k + " " + v));
  }

}
