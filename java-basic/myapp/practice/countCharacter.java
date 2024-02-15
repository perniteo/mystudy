package practice;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class countCharacter {

  public static void main(String[] args) {

    Map<String, Long> map = countChar("🍬🍬🍬🍬🍬Hello Fucker");

    for (Map.Entry<String, Long> entry : map.entrySet()) {
      System.out.println(entry.getKey() + " " + entry.getValue());
    }


  }

  public static Map<String, Long> countChar2(String str) {

    Map<String, Long> map = new HashMap<>();

    for (int i = 0; i < str.length(); i++) {
      int cp = str.codePointAt(i);
      String ch = String.valueOf(Character.toChars(cp));

      if (Character.charCount(cp) == 2) {
        i++;
      }

      map.compute(ch, (k, v) -> v == null ? 1 : ++v);

    }
    return map;
  }

  public static Map<String, Long> countChar(String str) {

    return str.codePoints().mapToObj(c -> String.valueOf(Character.toChars(c))).
        collect(Collectors.groupingBy(c -> c, Collectors.counting()));

  }


}
