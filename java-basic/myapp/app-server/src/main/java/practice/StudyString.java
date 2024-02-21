package practice;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StudyString {

  public static String MinMax(String str) {
    Object[] answer = Arrays.stream(str.split(" ")).
        mapToInt(Integer::parseInt).sorted().boxed()
        .toArray();
    //autoboxing
    return answer[0] + " " + answer[answer.length - 1];
  }

  public static boolean isPalindrome(String str) {
    return str.contentEquals(new StringBuilder(str).reverse());
    // return str.equals(new StringBuilder(str).reverse().toString());
  }

  public static boolean isPalindrome2(String str) {
    int n = str.length();

    for (int i = 0; i < n / 2; i++) {
      if (str.charAt(i) != str.charAt(n - i - 1)) {
        return false;
      }
    }
    return true;
  }

  public static boolean isPalindrome3(String str) {
//    return IntStream.range(0, str.length() / 2).
//        allMatch(i -> str.charAt(i) == str.charAt((str.length() - i - 1)));

    return IntStream.range(0, str.length() / 2).
        noneMatch(i -> str.charAt(i) != str.charAt((str.length() - i - 1)));
  }

  public static String removeDuplicate(String str) {
    StringBuilder sb = new StringBuilder();

    for (int i = 0; i < str.length(); i++) {
      if (sb.indexOf(String.valueOf(str.charAt(i))) == -1) {
        sb.append(str.charAt(i));
      }
    }
    return sb.toString();
  }

  public static String removeDuplicate2(String str) {
    return Arrays.stream(str.split("")).distinct().collect(Collectors.joining());
  }

  public static String removeCharacter(String str, char ch) {
    return str.chars().filter(c -> (char) c != ch).mapToObj(String::valueOf)
        .collect(Collectors.joining());
  }

  public static String removeCharacter(String str, String ch) {
    int cp = ch.codePointAt(0);

    return str.codePoints().filter(c -> c != cp)
        .mapToObj(c -> String.valueOf(Character.toChars(c))).
        collect(Collectors.joining());
  }


  public static void main(String[] args) {
    System.out.println(MinMax("-1 -1"));
    System.out.println(isPalindrome("Fucker"));
    System.out.println(isPalindrome("FuckerekcuF"));
    System.out.println(isPalindrome2("Fucker"));
    System.out.println(isPalindrome2("FuckerekcuF"));
    System.out.println(isPalindrome3("Fucker"));
    System.out.println(isPalindrome3("FuckerekcuF"));
    System.out.println(removeDuplicate("Hello"));
    System.out.println(removeDuplicate2("Hello"));
    System.out.println(removeCharacter("🍬🍬🍬yummy", "🍬"));
  }

}
