package practice;

import java.util.HashSet;
import java.util.Set;

public class permuteAndStore {

  public static void main(String[] args) {

    System.out.println(permuteAndStore1("HELICOPTER").size());

  }

  public static Set<String> permuteAndStore1(String str) {
    return permuteAndStore1("", str);
  }

  public static Set<String> permuteAndStore1(String prefix, String str) {

    Set<String> set = new HashSet<>();

    int n = str.length();

    if (n == 0) {
      set.add(prefix);
    } else {
      for (int i = 0; i < n; i++) {
        set.addAll(permuteAndStore1(prefix + str.charAt(i),
            str.substring(i + 1, n) + str.substring(0, i)));
      }
    }
    return set;
  }

}
