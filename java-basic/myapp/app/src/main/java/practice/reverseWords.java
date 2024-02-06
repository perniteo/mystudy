package practice;

public class reverseWords {

  public static void main(String[] args) {

    System.out.println(reverseWord("🍬🍬🍬Fu cker"));

  }

  public static String reverseWord(String str) {
//    StringBuilder sb = new StringBuilder();
//
//    for (int i = str.length() - 1; i >= 0; i--) {
//
//      int cp = str.codePointAt(i);
//
//      if (Character.charCount(cp) == 2) {
//        i--;
//      }
//
//      sb.append(String.valueOf(Character.toChars(cp)));
//    }
    return new StringBuilder(str).reverse().toString();
  }

}
