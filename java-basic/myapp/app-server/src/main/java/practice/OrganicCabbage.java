package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class OrganicCabbage {
  //Boolean 배열은 null로 초기화 됨

  static boolean[][] graph;
  static int count;
  static int row;
  static int column;

  static int[] dx = {0, 0, 1, -1};
  static int[] dy = {1, -1, 0, 0};

  static Boolean Validation(int nx, int ny) {
    return nx >= 0 && ny >= 0 && nx < row && ny < column;
  }

  static void search(int x, int y) {
    for (int i = 0; i < 4; i++) {
      int nx = x + dx[i];
      int ny = y + dy[i];
      //&& 비교 연산은 앞부터 검사하는데 false 면 뒤는 검사 안 함 --> 순서 중요
      if (Validation(nx, ny) && graph[nx][ny]) {
        graph[nx][ny] = false;
        search(nx, ny);
      }
    }

  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int c = Integer.parseInt(br.readLine());

    for (int i = 0; i < c; i++) {
      count = 0;
      StringTokenizer st = new StringTokenizer(br.readLine());

      row = Integer.parseInt(st.nextToken());
      column = Integer.parseInt(st.nextToken());
      graph = new boolean[row][column];

      int edge = Integer.parseInt(st.nextToken());
      for (int j = 0; j < edge; j++) {
        StringTokenizer st2 = new StringTokenizer(br.readLine());

        graph[Integer.parseInt(st2.nextToken())][Integer.parseInt(st2.nextToken())] = true;
      }

      for (int a = 0; a < row; a++) {
        for (int b = 0; b < column; b++) {
          if (graph[a][b]) {
            graph[a][b] = false;
            count++;
            search(a, b);
          }
        }
      }
      System.out.println(count);

    }
  }
}
