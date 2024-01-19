// 활용 - 지정한 폴더에서 .class 파일만 찾아 출력하라.
package io.ex01;

import java.io.File;

public class Exam0730 {

  public static void main(String[] args) throws Exception {

    File dir = new File("java-basic/app/bin/main");
    System.out.println(dir.getCanonicalPath());

    printClasses(dir);
  }

  static void printClasses(File dir) {

    File[] files = dir.listFiles((pathname) -> pathname.isDirectory()
        || (pathname.isFile() && pathname.getName().endsWith(".class")));
    // new FileFilter() {
    // @Override
    // public boolean accept(File pathname) {
    // return pathname.isDirectory()
    // || (pathname.isFile() && pathname.getName().endsWith(".class"));
    // }
    // });

    for (File file : files) {
      if (file.isDirectory()) {
        printClasses(file);
      } else {
        System.out.printf("%s\n", file.getName());
      }
    }
  }
}


