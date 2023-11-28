package com.eomcs.lang.ex05;

import java.util.Scanner;

// # 증감 연산자 : 전위(pre-fix) 증감 연산자 응용 II
//
public class Exam0682 {
  public static void main(String[] args) throws Exception {
    // 주의!
    // 1) pre-fix 연산자나 post-fix 연산자를 리터럴에 적용할 수 없다.
    // int x = ++100; // 컴파일 오류!
    // x = 100++; // 컴파일 오류!

    // 2) 변수에 동시에 적용할 수 없다.
    // - 실행된 값에 대해 전위/후위 연산자를 적용할 수 없기 때문이다.
    int y = 100;
    // ++y++; // 컴파일 오류!
    // (++y)++; // 컴파일 오류!
    // ++(y++); // 컴파일 오류!
    int x = y++;

    // 비트 연산

    // System.out.printf("x : %d, y : %d\n", x, y);
    // System.out.println(args.length);
    // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    // StringTokenizer st = new StringTokenizer(br.readLine());
    // int a = Integer.parseInt(st.nextToken());
    // int b = Integer.parseInt(st.nextToken());
    // System.out.println(a ^ b);

    // 정수 배열 최대값 구하기

    // int temp = 0;
    // int[] array = {1, 5, 3, 8, 2};
    // for (int i = 0; i < array.length; i++) {
    // if (array[i] >= temp) {
    // temp = array[i];
    // }
    // }
    // System.out.println(temp);

    // 2중 배열

    // int[][] array = {{95, 86}, {83, 92, 96}, {78, 83, 93, 87, 88}};
    // int sum_Num = 0;
    // int avr = 0;
    // int cnt = 0;
    // for (int i = 0; i < array.length; i++) {
    // for (int j = 0; j < array[i].length; j++) {
    // sum_Num += array[i][j];
    // cnt += 1;
    // }
    // }
    // System.out.printf("전체 합 : %d\n평균 : %d", sum_Num, sum_Num / cnt);

    // 간단한 예제

    Scanner sc = new Scanner(System.in);
  }
}
