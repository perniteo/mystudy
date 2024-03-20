package practice;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SearchMaze {

  static int row;
  static int column;
  static int[][] graph;
  static boolean[][] visited;

  static int[] dx = {0, 0, 1, -1};
  static int[] dy = {-1, 1, 0, 0};

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine(), " ");
    row = Integer.parseInt(st.nextToken());
    column = Integer.parseInt(st.nextToken());

    graph = new int[row][column];
    visited = new boolean[row][column];

    for (int i = 0; i < row; i++) {
      String str = br.readLine();
      for (int j = 0; j < column; j++) {
        graph[i][j] = str.charAt(j);
      }
    }
    bfs(new Sub(0, 0, 1));


  }

  public static void bfs(Sub sub) {
    Sub[] subs = new Sub[row * column];
    int a = -1;
    int b = -1;

    subs[++a] = sub;
    visited[sub.x][sub.y] = true;

    while (a != b) {
      Sub tmp = subs[++b];

      if (tmp.x == row - 1 && tmp.y == column - 1) {
        System.out.println(tmp.dist);
        break;
      }

      for (int i = 0; i < 4; i++) {
        int nx = tmp.x + dx[i];
        int ny = tmp.y + dy[i];

        if (validation(nx, ny) && !visited[nx][ny] && graph[nx][ny] == '1') {
          visited[nx][ny] = true;
          subs[++a] = new Sub(nx, ny, tmp.dist + 1);
        }
      }

    }

  }

  public static boolean validation(int x, int y) {
    return x >= 0 && y >= 0 && x < row && y < column;
  }

  public static class Sub {

    int x, y, dist;

    public Sub(int x, int y, int dist) {
      this.x = x;
      this.y = y;
      this.dist = dist;
    }
  }

}
