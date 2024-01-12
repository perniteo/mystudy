package practice;

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

  public static void main(String[] args) {
    String str = "Hello";
    str.chars().forEach(System.out::println);
    Prac p = new Prac();
    System.out.printf("%d,%d,%d", p.a, p.b, p.c);
    Prac r = new Prac();
  }

}
