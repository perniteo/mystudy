package bitcamp.io;

import java.io.FileNotFoundException;
import java.io.IOException;

public class BufferedDataOutputStream extends DataOutputStream {

  private final byte[] buffer = new byte[8192];
  int size;

  public BufferedDataOutputStream(String name) throws FileNotFoundException {
    super(name);
  }

  @Override
  public void write(int b) throws IOException {
    if (size == buffer.length) {
      flush();
    }
    buffer[size++] = (byte) b;
  }

  @Override
  public void write(byte[] bytes) throws IOException {
    for (byte b : bytes) {
      if (size == buffer.length) {
        flush();
      }
      buffer[size++] = b;
    }
  }

  public void flush() throws IOException {
    super.write(buffer, 0, size);
    size = 0;
  }

  @Override
  public void close() throws IOException {
    flush();
    super.close();
  }
}
