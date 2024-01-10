// 메서드 레퍼런스 - 스태틱 메서드 레퍼런스
package com.eomcs.oop.ex12;

import java.util.function.BiFunction;

public class Exam0510 {

  static class MyCalculator {
    public static int plus(int a, int b) {return a + b;}
    public static int minus(int a, int b) {return a - b;}
    public static int multiple(int a, int b) {return a * b;}
    public static int divide(int a, int b) {return a / b;}
  }

  interface Calculator {
    int compute(int x, int y);
  }

  public static void main(String[] args) {
    

    class MathOperations {
      static int multiply(int a, int b) {
          return a * b;
      }
  }
  
  // 메서드 참조
  BiFunction<Integer, Integer, Integer> multiplyFunction = MathOperations::multiply;
  System.out.println(multiplyFunction.apply(1, 3));
  
    // 메서드 한 개짜리 인터페이스의 구현체를 만들 때,

    // 1) 익명 클래스 활용
    Calculator obj1 = new Calculator() {
      @Override
      public int compute(int x, int y) {
        return x * y;
      }
    };

    // 2) 람다 문법 활용
    Calculator obj2 = (x, y) -> x * y;

    // 3) 기존에 작성한 클래스의 스태틱 메서드를 재활용하기
    // => 인터페이스의 메서드 규격과 일치하는 메서드가 있다면,
    //    그 메서드를 람다 구현체로 대체할 수 있다.
    // => 새로 코드를 작성할 필요가 없어 매우 편리하다.
    // => 규격? 메서드의 파라미터 타입/개수/순서, 리턴 타입
    // => 문법:
    //    클래스명::메서드명
    Calculator c1 = MyCalculator::plus; // MyCalculator의 스태틱 메서드인 plus()를 가지고 구현체를 자동 생성!
    //    Calculator c = new Calculator() {
    //      @Override
    //      public int compute(int x, int y) {
    //        return MyCalculator.plus(x, y);
    //      }
    //    };
    // Calculator c= (a, b) -> MyCalculator.plus(a, b);
    Calculator c2 = MyCalculator::minus;
    Calculator c3 = MyCalculator::multiple;
    Calculator c4 = MyCalculator::divide;

    System.out.println(c1.compute(200, 17)); // compute() ==> plus()
    System.out.println(c2.compute(200, 17)); // compute() ==> minus()
    System.out.println(c3.compute(200, 17)); // compute() ==> multiple()
    System.out.println(c4.compute(200, 17)); // compute() ==> divide()
  }
}


