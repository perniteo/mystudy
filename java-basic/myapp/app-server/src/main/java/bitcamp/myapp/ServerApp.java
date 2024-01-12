package bitcamp.myapp;

import bitcamp.myapp.dao.json.AssignmentImpl;
import bitcamp.myapp.dao.json.BoardDaoImpl;
import bitcamp.myapp.dao.json.MemberDaoImpl;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ServerApp {

  Map<String, Object> daoMap = new HashMap<>();
  Gson gson;

  public ServerApp() {
    daoMap.put("board", new BoardDaoImpl("board.json"));
    daoMap.put("assignment", new AssignmentImpl("assignment.json"));
    daoMap.put("member", new MemberDaoImpl("member.json"));
    daoMap.put("greeting", new BoardDaoImpl("greeting.json"));

    gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
  }

  public static void main(String[] args) throws Exception {
    new ServerApp().run();
  }

  void run() throws Exception {
    System.out.println("[Server System]");
    try {
      ServerSocket serverSocket = new ServerSocket(7777);
      System.out.println("ServerSocket");
      Socket socket = serverSocket.accept();
      System.out.println("Accept");

      DataInputStream in = new DataInputStream(socket.getInputStream());
      DataOutputStream out = new DataOutputStream(socket.getOutputStream());

      while (true) {
        System.out.println("---------------------");
        String dataName = in.readUTF();
        String command = in.readUTF();
        String value = in.readUTF();

        Object dao = daoMap.get(dataName);

        Method[] methods = dao.getClass().getDeclaredMethods();

        Method commandHandler = null;

        for (Method method : methods) {
          if (method.getName().equals(command)) {
            commandHandler = method;
            break;
          }
        }

        Parameter[] params = Objects.requireNonNull(commandHandler).getParameters();

        Object[] args = new Object[params.length];

        if (params.length > 0) {
          Class<?> paramType = params[0].getType();

          Object paramValue = gson.fromJson(value, paramType);

          args[0] = paramValue;
        }

        Class<?> returnType = commandHandler.getReturnType();

        Object returnValue = commandHandler.invoke(dao, args);

        out.writeUTF("200");
        out.writeUTF(gson.toJson(returnValue));
      }

    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("Error");
    }
//    System.out.println("Current working directory: " + System.getProperty("user.dir"));
//    System.out.println("board.json exists: " + new File("board.json").exists());
//    System.out.println("assignment.json exists: " + new File("assignment.json").exists());
//    System.out.println("member.json exists: " + new File("member.json").exists());
//    System.out.println("greeting.json exists: " + new File("greeting.json").exists());
  }

}

