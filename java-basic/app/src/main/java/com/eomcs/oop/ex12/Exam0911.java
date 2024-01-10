// List 와 forEach() - forEach() 사용 전 II
package com.eomcs.oop.ex12;

import java.util.ArrayList;
import java.util.function.Consumer;

public class Exam0911 {

  public static void main(String[] args) {
    ArrayList<String> names = new ArrayList<>();
    names.add("홍길동");
    names.add("임꺽정");
    names.add("유관순");
    names.add("김구");
    names.add("안중근");

    Consumer<String> MyConsumer = System.out::println;

    for (String name : names) {
      MyConsumer.accept(name);
    }
  }
}