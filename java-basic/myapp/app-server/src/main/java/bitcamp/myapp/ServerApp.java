package bitcamp.myapp;

import bitcamp.RequestException;
import bitcamp.myapp.dao.json.AssignmentDaoImpl;
import bitcamp.myapp.dao.json.BoardDaoImpl;
import bitcamp.myapp.dao.json.MemberDaoImpl;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class ServerApp {

  Map<String, Object> daoMap = new HashMap<>();
  Gson gson;

  public ServerApp() {
    daoMap.put("board", new BoardDaoImpl("board.json"));
    daoMap.put("assignment", new AssignmentDaoImpl("assignment.json"));
    daoMap.put("member", new MemberDaoImpl("member.json"));
    daoMap.put("greeting", new BoardDaoImpl("greeting.json"));

    gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
  }

  public static void main(String[] args) throws Exception {
    new ServerApp().run();
  }

  private Method findMethod(Class<?> clazz, String name) {
    Method[] methods = clazz.getDeclaredMethods();
    for (Method m : methods) {
      if (m.getName().equals(name)) {
        return m;
      }
    }
    throw new RequestException("Not exist method");
  }

  private Object[] getArguments(Method m, String json) {
    Parameter[] params = m.getParameters();

    Object[] args = new Object[params.length];

    if (params.length > 0) {
      Class<?> paramType = params[0].getType();
      Object paramValue = gson.fromJson(json, paramType);
      args[0] = paramValue;
    }
    return args;
  }

  private void processRequest(DataInputStream in, DataOutputStream out) throws IOException {
    String dataName = in.readUTF();
    String command = in.readUTF();
    String value = in.readUTF();
    try {
      Object dao = daoMap.get(dataName);
      if (dao == null) {
        throw new RequestException("Not exist data");
      }

      Method commandHandler = findMethod(dao.getClass(), command);

      Object[] args = getArguments(commandHandler, value);

      Class<?> returnType = commandHandler.getReturnType();
      System.out.printf("ReturnType : %s\n", returnType);

      Object returnValue = commandHandler.invoke(dao, args);

      out.writeUTF("200");
      out.writeUTF(gson.toJson(returnValue));

    } catch (RequestException e) {
      out.writeUTF("400");
      out.writeUTF(gson.toJson(e.getMessage()));
    } catch (Exception e) {
      out.writeUTF("500");
      out.writeUTF(gson.toJson(e.getMessage()));
    }

  }

  private void service(Socket socket) throws IOException {
    try (Socket s = socket;
        DataInputStream in = new DataInputStream(socket.getInputStream());
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());) {
      System.out.println("Accept socket");

      System.out.println("---------------------");
      processRequest(in, out);

    } catch (Exception e) {
      System.out.println("Connect Error");
    }
  }

  void run() throws Exception {
    System.out.println("[Server System]");
    try (ServerSocket serverSocket = new ServerSocket(7777)) {
      System.out.println("ServerSocket");

      while (true) {
        Socket socket = serverSocket.accept();
        new Thread(() -> {
          try {
            service(socket);
          } catch (IOException e) {
            throw new RuntimeException(e);
          }
        }).start();
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

