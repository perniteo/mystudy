package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Zoning {

  static boolean[][] graph;
  static List<Integer> answer;
  static int range;
  static int count;

  static int[] dx = {0, 0, 1, -1};
  static int[] dy = {-1, 1, 0, 0};

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    answer = new ArrayList<>();
    range = Integer.parseInt(br.readLine());
    graph = new boolean[range][range];
    count = 0;

    for (int i = 0; i < range; i++) {
      char[] line = br.readLine().toCharArray();
      for (int j = 0; j < range; j++) {
        if (line[j] == '1') {
          graph[i][j] = true;
        }
      }
    }

    for (int i = 0; i < range; i++) {
      for (int j = 0; j < range; j++) {
        if (graph[i][j]) {
          graph[i][j] = false;
          count++;
          search(i, j);
        } else {
          if (count != 0) {
            answer.add(count);
            count = 0;
          }
        }
      }
      if (count != 0) {
        answer.add(count);
        count = 0;
      } // 라인이 넘어갈 때도 검사해야했음.. 이 부분 예외처리 검사하는데 20분 소요

    }

    Collections.sort(answer);
    System.out.println(answer.size());
    for (int i : answer) {
      System.out.println(i);
    }

  }

  static Boolean Validation(int nx, int ny) {
    return nx >= 0 && ny >= 0 && nx < range && ny < range;
  }

  static void search(int x, int y) {
    for (int i = 0; i < 4; i++) {
      int nx = dx[i] + x;
      int ny = dy[i] + y;
      if (Validation(nx, ny) && graph[nx][ny]) {
        graph[nx][ny] = false;
        count++;
        search(nx, ny);
      }
    }
  }
}
