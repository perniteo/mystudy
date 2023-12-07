package bitcamp.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Prompt {

  BufferedReader keyIn;

  public Prompt(InputStream in) {
    keyIn = new BufferedReader(new InputStreamReader(in));
  }

  public String input(String title, Object... args) throws Exception {
    System.out.printf(title, args);
    return this.keyIn.readLine();
  }

  public int inputInt(String title, Object... args) throws Exception {
    String str = this.input(title, args);
    return Integer.parseInt(str);
  }

  public float floatInput(String title, Object... args) throws Exception {
    String str = this.input(title, args);
    return Float.parseFloat(str);
  }

  public boolean booleanInput(String title, Object... args) throws Exception {
    String str = this.input(title, args);
    return Boolean.parseBoolean(str);
  }

  public void close() throws Exception {
    this.keyIn.close();
  }
}
