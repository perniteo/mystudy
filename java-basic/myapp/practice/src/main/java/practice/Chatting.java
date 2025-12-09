package practice;

public class Chatting {

  void startChat(String chatId) {
    String nickName = chatId;
    Chat chat = new Chat() {
      @Override
      public void start() {
        while (true) {
          String inputData = "안녕하세요";
          String message = "[" + nickName + "]" + inputData;
          sendMessage(message);
        }
      }

      @Override
      public void sendMessage(String message) {
        System.out.println(message);
      }
    };
    chat.start();
  }

  interface Chat {

    void start();

    void sendMessage(String message);
  }
}