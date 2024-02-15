package practice;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Prac {

  int a;
  int b;
  int c;

  {
    a = 100;
    System.out.println("Instance block");
  }

  Prac() {
    a = 50;
    System.out.println("Constructor");
  }

  public static void main(String[] args) throws UnknownHostException {

    InetAddress[] inetAddress = InetAddress.getAllByName("www.naver.com");

    for (InetAddress ia : inetAddress) {
      System.out.println(ia);
    }

    String str = "Hello";
    str.chars().forEach(System.out::println);
    Prac p = new Prac();
    System.out.printf("%d,%d,%d", p.a, p.b, p.c);
    Prac r = new Prac();
  }

}
