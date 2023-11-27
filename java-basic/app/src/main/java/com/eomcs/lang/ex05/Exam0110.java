package com.eomcs.lang.ex05;

// # 산술 연산자 : +, -, *, /, %
//
public class Exam0110 {
  public static void main(String[] args) {
    System.out.println(100 + 27);
    System.out.println(100 - 27);
    System.out.println(100 * 27);
    System.out.println(100 / 27);
    System.out.println(100 % 27); // 나눈 나머지
    String hobby = "여행";
    hobby = null;
    System.out.println(hobby);
    String kind1 = "자동차";
    String kind2 = kind1;
    kind1 = null;
    System.out.println(kind1 + kind2);
    String str = null;
    char a = 'A';
    char b = 'B';
    int c = a + b;
    System.out.println(kind2.charAt(1));
    int intValue1 = 2100000000;
    long longValue1 = 2100000000;
    System.out.println(intValue1 + longValue1);
    float floatValue1 = 3.14f;
    System.out.println((double) intValue1 + (double) floatValue1);
  }
}
