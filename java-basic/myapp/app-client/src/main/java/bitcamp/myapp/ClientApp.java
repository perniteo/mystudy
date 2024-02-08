package bitcamp.myapp;

import bitcamp.util.Prompt;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class ClientApp {

  String serverAddress;
  int port;

  public static void main(String[] args) throws Exception {
    new ClientApp().Server("localhost").Port(7777).run();
  }

  ClientApp Server(String serverAddress) {
    this.serverAddress = serverAddress;
    return this;
  }

  ClientApp Port(int port) {
    this.port = port;
    return this;
  }

  void run() throws Exception {
    try (Socket socket = new Socket(serverAddress, port);
        DataInputStream in = new DataInputStream(socket.getInputStream());
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        Prompt prompt = new Prompt(System.in)) {

      while (true) {
        String response = in.readUTF();
        if (response.equals("[[quit!]]")) {
          break;
        }
        System.out.print(response);

        String input = prompt.input("");
        out.writeUTF(input);
      }
    }
  }
}

