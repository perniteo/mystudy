// character stream - 문자 단위로 읽기
package com.eomcs.io.ex03;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class Exam0130 {

  public static void main(String[] args) throws Exception {
    // 1) 파일의 데이터를 읽을 객체를 준비한다.
    // => 읽어들일 파일의 문자집합이 file.encoding 환경변수에 지정된 값과 다르다면,
    // 다음과 같이 출력 스트림을 생성할 때 파일의 문자집합을 알려줘라.
    FileReader in = new FileReader("sample/utf8.txt"); // 41 42 b0 a1 b0 a2

    int ch;

    while ((ch = in.read()) != -1) {
      System.out.printf("%02x ", ch);
      System.out.println(ch);
    }

    in.close();

    try (BufferedReader reader = new BufferedReader(
        new InputStreamReader(new FileInputStream("sample/utf8.txt"), StandardCharsets.UTF_8))) {
      String line;
      int aaa;
      while ((line = reader.readLine()) != null) {
        // 한 줄씩 읽어온 문자열 출력
        System.out.println(line);
        for (int i = 0; i < line.length(); i++) {
          System.out.println(line.charAt(i));
        }
      }

    } catch (IOException e) {
      e.printStackTrace();
    }

    // System.out.printf("%04x, %04x, %04x, %04x\n", ch1, ch2, ch3, ch4);
  }
}


