package practice;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class inspectDigit {

  public static void main(String[] args) {

    Map<String, String> map = new HashMap<>();
    map.put("{", "}");

    LinkedList<String> linkedList = new LinkedList<>();

    System.out.println(inspectDigit1("Fucker"));
    System.out.println(inspectDigit1("F123"));
    System.out.println(inspectDigit1("🍬123"));
    System.out.println(inspectDigit1("312321313321321000"));

  }

  public static boolean inspectDigit1(String str) {
    return str.chars().allMatch(Character::isDigit);
  }

}
