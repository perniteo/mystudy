package practice;

import java.io.IOException;
import java.util.Calendar;

public class Main {

  public static void main(String[] args) throws IOException {
    StringBuilder sb = new StringBuilder();
    Calendar cal = Calendar.getInstance();
    Calendar cal1 = Calendar.getInstance();
    System.out.println(cal == cal1);
    System.out.println(cal.getCalendarType());
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

  @Override
  public int hashCode() {
    int hashCode = no + name.hashCode();
    return hashCode;
  }
}
