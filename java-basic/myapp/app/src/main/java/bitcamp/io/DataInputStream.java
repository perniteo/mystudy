package bitcamp.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class DataInputStream extends FileInputStream {
  java.io.DataInputStream

  public DataInputStream(String name) throws FileNotFoundException {
    super(name);
  }

  public short readShort() throws IOException {
    return (short) (read() << 8 | read());
  }

  public int readInt() throws IOException {
    return read() << 24 | read() << 16 | read() << 8 | read();
  }

  public long readLong() throws IOException {
    return (long) read() << 56 | (long) read() << 48 | (long) read() << 40 | (long) read() << 32
        | (long) read() << 24 | read() << 16 | read() << 8 | read();
  }
  public boolean readBoolean() throws IOException {
    return read() == 1;
  }

  public String readUTF() throws IOException {
    return
  }
}
