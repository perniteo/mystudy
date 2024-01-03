package bitcamp.io;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class DataOutputStream extends FileOutputStream {

  public DataOutputStream(String name) throws FileNotFoundException {
    super(name);
  }

  public void writeUTF(String value) throws IOException {
    byte[] bytes = value.getBytes(StandardCharsets.UTF_8);
    writeShort(bytes.length);
    write(bytes);
  }

  public void writeLong(int value) throws IOException {
    write(value >> 64);
    write(value >> 56);
    write(value >> 48);
    write(value >> 40);
    write(value >> 32);
    write(value >> 24);
    write(value >> 16);
    write(value >> 8);
    write(value);
  }

  public void writdInt(int value) throws IOException {
//    write(value >> 32);
    write(value >> 24);
    write(value >> 16);
    write(value >> 8);
    write(value);
  }

  public void writeShort(int value) throws IOException {
    write(value >> 8);
    write(value);
  }

}
