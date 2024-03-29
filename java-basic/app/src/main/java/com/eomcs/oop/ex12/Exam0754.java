// 메서드 레퍼런스 - 생성자 레퍼런스 활용
package com.eomcs.oop.ex12;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.function.Supplier;

public class Exam0754 {

  @SuppressWarnings("unchecked")
  static <T> Collection<T> prepareNames(Supplier<Collection<T>> factory, T... values) {
    Collection<T> list = factory.get(); // => new ArrayList<String>()
    for (T value : values) {
      list.add(value);
    }
    return list;
  }

  static <T> void print(Iterator<T> i) {
    while (i.hasNext()) {
      System.out.print(i.next() + ",");
    }
    System.out.println();
  }

  public static void main(String[] args) {
    print(prepareNames(ArrayList<String>::new, "홍길동", "임꺽정", "유관순", "임꺽정").iterator());

    // 4) 람다 문법으로 Supplier 구현체 만들기
    Collection<String> list = prepareNames(
        () -> new ArrayList<String>(), 
        "홍길동", "임꺽정", "유관순", "임꺽정");
    print(list.iterator());

  }

}


