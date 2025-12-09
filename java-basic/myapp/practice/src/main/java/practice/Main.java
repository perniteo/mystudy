package practice;

import java.io.IOException;
import java.util.Calendar;
import java.util.Properties;
import java.util.Set;

public class Main {

  public static void compute(String operate) {
    try {
      switch (operate) {
        case "+", "-":
          break;
      }

    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  public static void error1() throws InsufficientException {
    throw new InsufficientException("fucking error");
  }

  public static void main(String[] args) throws IOException, InsufficientException {

    try {
      error1();
    } catch (InsufficientException e) {
      System.out.println(e.getMessage());
    }
    try {
      throw new InsufficientException("fucking error");
    } catch (InsufficientException e) {
      System.out.println(e.getMessage());
    }
    compute("3");
    System.out.println(Math.random());
    Properties props = System.getProperties();
    Set<Object> keys = props.keySet();
    for (Object object : keys) {
      String key = (String) object;
      String value = System.getProperty(key);
      System.out.printf("%-40s%s\n", key, value);
    }

    Experiment ex = new Experiment("Ssss");
    Experiment ex1 = new Experiment("Ssss");
    System.err.println(ex.hashCode() == ex1.hashCode());
    System.err.println(ex == ex1);
    Person person = new Person("Fucker", 13);
    System.err.println(person.hashCode());

    String str = "Sss";
    String str1 = new String("Sss");
    String str2 = "Sss";
    System.err.println(str.hashCode() == str1.hashCode());
    System.err.println(str == str1);
    System.err.println(str == str2);
    StringBuilder sb = new StringBuilder();
    Calendar cal = Calendar.getInstance();
    Calendar cal1 = Calendar.getInstance();
    System.err.println(cal == cal1);
    System.err.println(cal.getCalendarType());
//    AutoCloseable
    System.in.close();
//    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//    StringTokenizer st = new StringTokenizer(br.readLine());
//    int cookingTime = Integer.parseInt(br.readLine());
//    StringBuilder str = new StringBuilder();
//    while (st.hasMoreTokens()) {
//      str.append(st.nextToken());
//    }
//    int now = Integer.parseInt(str.toString());
//    int hour;
//    int minute;
//    if (cookingTime >= 60) {
//      hour = cookingTime / 60;
//      minute = cookingTime % 60;
//    } else {
//      hour = 0;
//      minute = cookingTime;
//    }
//    now += hour * 100 + minute;
//    System.out.println(now);
//    class Chatting {
//
//      void startChat(String chatId) {
//        String nickName = null;
//        nickName = chatId;
//
//        Chat chat = new Chat() {
//          @Override
//          public void start() {
//            while (true) {
//              String inputData = "안녕하세요";
//              String message = "[" + nickName + "]" + inputData;
//              sendMessage(message);
//            }
//          }
//
//        };
//        chat.start();
//      }
//
//      class Chat {
//
//        void start() {
//        }
//
//        ;
//
//        void sendMessage(String message) {
//        }
//
//        ;
//      }
//    }
  }

  public static class InsufficientException extends Exception {

    InsufficientException() {
    }

    InsufficientException(String message) {
      super(message);
    }
  }

//  @Override
//  public int hashCode() {
//    int hashCode = no + name.hashCode();
//    return hashCode;
//  }

  public static class Experiment {

    String str;

    Experiment(String str) {
      this.str = str;
    }
  }

  public record Person(String name, int age) {

  }
}
