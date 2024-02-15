package practice;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FirstNonRepeatedFinder {

  private static final int ASCII_CODES = 256;

  public static void main(String[] args) {

    System.out.println(firstNonRepeatedCharacter("🍬Hello Fucker"));
    System.out.println(firstNonRepeatedCharacter2("Hello Fucker"));
    System.out.println(firstNonRepeatedCharacter3("🍬Hello Fucker"));
    System.out.println(firstNonRepeatedCharacter4("🍬Hello Fucker"));

  }

  public static String firstNonRepeatedCharacter4(String str) {

    return str.codePoints()
        .mapToObj(Character::toString)
        .collect(
            Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()))
        .entrySet().stream()
        .filter(c -> c.getValue() == 1)
        .findFirst()
        .map(Map.Entry::getKey)
        .orElse(null);
//    Map<String, Long> map = str.codePoints().mapToObj(c -> String.valueOf(Character.toChars(c))).
//        collect(Collectors.groupingBy(c -> c, LinkedHashMap::new, Collectors.counting()));

//    for (Map.Entry<String, Long> entry : map.entrySet()) {
//      if (entry.getValue() == 1) {
//        return entry.getKey();
//      }
//    }
//    return null;
  }

  public static char firstNonRepeatedCharacter3(String str) {
    Map<Character, Integer> map = new LinkedHashMap<>();

    for (char ch : str.toCharArray()) {
      map.compute(ch, (k, v) -> v == null ? 1 : ++v);
    }

    for (Map.Entry<Character, Integer> entry : map.entrySet()) {
      if (entry.getValue() == 1) {
        return entry.getKey();
      }
    }
    return Character.MIN_VALUE;
  }

  public static char firstNonRepeatedCharacter2(String str) {

    int[] list = new int[ASCII_CODES];

    Arrays.fill(list, -1);

    for (int i = 0; i < str.length(); i++) {
      char ch = str.charAt(i);
      if (list[ch] == -1) {
        list[ch] = i;
      } else {
        list[ch] = -2;
      }
    }
    int position = Integer.MAX_VALUE;

    for (int i = 0; i < ASCII_CODES; i++) {
      if (list[i] >= 0) {
        position = Math.min(list[i], position);
      }
    }
    return position == Integer.MAX_VALUE ? Character.MIN_VALUE : str.charAt(position);

  }


  public static String firstNonRepeatedCharacter(String str) {

    Map<Integer, Long> map = str.codePoints().boxed().
        collect(
            Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()));

    int cp = map.entrySet().stream().filter(c -> c.getValue() == 1).findFirst()
        .map(Map.Entry::getKey).orElse(
            (int) Character.MIN_VALUE);

    return String.valueOf(Character.toChars(cp));

  }

}
