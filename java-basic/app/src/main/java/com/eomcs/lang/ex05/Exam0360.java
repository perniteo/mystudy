// 논리 연산자 : 조건문과 비트 연산
package com.eomcs.lang.ex05;

import java.util.Scanner;

public class Exam0360 {
  public static void main(String[] args) {
    // &&, ||, ! 의 피연산자(operand)는 반드시 boolean 이어야 한다.
    // 그리고 계산 결과는 boolean이다.
    boolean r;
    // r = 10 && 20; // 컴파일 오류!
    // r = 10 || 20; // 컴파일 오류!

    // &, |, ^, ~(not) 의 피연산자가 정수라면
    // 비트 연산자로 동작한다.

    // r = 10 & 20; // 컴파일 오류! 비트 연산의 결과는 정수이다.

    int r2 = 10 & 20; // OK!

    // float r3 = 10.2f & 20.3f; // 컴파일 오류!
    int[] arr1 = {1, 2, 3};
    int[] arr2 = {1, 2, 3};
    int[] arr3 = {};
    int[] arr4 = new int[0];
    System.out.println(arr1);
    System.out.println(arr2);

    int balance = 0;
    Scanner scanner = new Scanner(System.in);
    boolean run = true;
    while (run) {
      System.out.print("""
          --------------------------------------
          1. 예금 | 2. 출금 | 3. 잔고 | 4. 종료
          --------------------------------------
          """);
      System.out.print("선택 > ");
      int inputInt = scanner.nextInt();
      switch (inputInt) {
        case 1:
          // System.out.println(inputInt);
          System.out.print("예금액 > ");
          balance += scanner.nextInt();
          // System.out.println();
          break;

        case 2:
          // System.out.println(inputInt);
          System.out.print("출금액 > ");
          balance -= scanner.nextInt();
          // System.out.println();
          break;

        case 3:
          System.out.println("잔고 > " + balance);
          break;

        case 4:
          System.out.println("\n프로그램 종료");
          run = false;
          break;
        // default:
      }
      System.out.println();
    }
  }
}

