package bitcamp.util;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Date;
import java.util.Stack;

public class Prompt implements AutoCloseable {

  private final DataInputStream in;
  private final DataOutputStream out;
  private final StringWriter stringWriter = new StringWriter();
  private final PrintWriter writer = new PrintWriter(stringWriter);
  Stack<String> stack = new Stack<>();

  public Prompt(DataInputStream in, DataOutputStream out) {
    this.in = in;
    this.out = out;
  }

  public String getMenuPath() {
    return String.join("/", this.stack.toArray(new String[0]));
  }

  public void pushPath(String path) {
    stack.push(path);
  }

  public String popPath() {
    return stack.pop();
  }

  public String input(String str, Object... args) {
    try {
      printf(str, args);
      end();
      return input();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public int inputInt(String str, Object... args) {
    return Integer.parseInt(this.input(str, args));
  }

  public float inputFloat(String str, Object... args) {
    return Float.parseFloat(this.input(str, args));
  }

  public boolean inputBoolean(String str, Object... args) {
    return Boolean.parseBoolean(this.input(str, args));
  }

  public Date inputDate(String str, Object... args) {
    return Date.valueOf(this.input(str, args));
  }

  // ------------------------------------------------------------

  public void print(String str) {
    writer.print(str);
  }

  public void println(String str) {
    writer.println(str);
  }

  public void printf(String str, Object... args) {
    writer.printf(str, args);
  }

  public void end() throws Exception {
    // PrintWriter를 통해 출력한 내용은 StringWriter에 쌓여있다.
    // StringWriter에 쌓여있는 있는 문자열을 꺼낸다.
    StringBuffer buf = stringWriter.getBuffer();
    String content = buf.toString();

    // StringWriter에 쌓여있는 문자열을 꺼낸 후 버퍼를 다시 0으로 초기화시킨다.
    buf.setLength(0);

    // 버퍼에서 꺼낸 문자열을 클라이언트로 전송한다.
    // 즉 서버의 응답이 완료된다.
    out.writeUTF(content);
  }

  public String input() throws Exception {
    return in.readUTF();
  }

  public int inputInt() throws Exception {
    return Integer.parseInt(this.input());
  }

  public float inputFloat() throws Exception {
    return Float.parseFloat(this.input());
  }

  public boolean inputBoolean() throws Exception {
    return Boolean.parseBoolean(this.input());
  }

  public Date inputDate() throws Exception {
    return Date.valueOf(this.input());
  }

  public void close() throws Exception {
    writer.close();
    stringWriter.close();
  }
}
