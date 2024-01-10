// lambda 문법 : 익명 클래스를 사용할 수 있는 곳에는 모두 람다 사용 가능
package com.eomcs.oop.ex12;

public class Exam0160 {
  interface A {
    void print();
  }

  // 스태틱 필드
  // static A obj1 = () -> System.out.println("스태틱 필드");

  static A obj1 = new A() {
    @Override
   public void print() {
      System.out.println("static field");
    }
  };

  //인스턴스 필드
  // A obj2 = () -> System.out.println("인스턴스 필드");

  A obj2 = new A() {
    @Override
    public void print() {
      System.out.println("instance field");
    }
  };

  public static void main(final String[] args) {
    Exam0160 a = new Exam0160();

    obj1.print();

    a.obj2.print();

    // 로컬 변수
    // A obj3 = () -> System.out.println("로컬 변수!");

    A obj3 = new A() {
      @Override
      public void print() {
        System.out.println("local variable");
      }
    };

    obj3.print();

    // 파라미터
    // m1(() -> System.out.println("파라미터"));
    m1(new A() {
      @Override
      public void print() {
        System.out.println("parameter");
      }
    });

    // 리턴 값
    A obj4 = m2();
    obj4.print();
  }

  static void m1(final A obj) {
    obj.print();
  }

  static A m2() {
    // 리턴 문장
    // return () -> System.out.println("리턴 문장");
    return new A() {
      @Override
      public void print() {
        System.out.println("return");
      }
    };
  }
}
