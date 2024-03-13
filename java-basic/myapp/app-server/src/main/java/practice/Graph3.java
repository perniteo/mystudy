package practice;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

//성능 개선
public class Graph3 {

  private static int[][] graph;
  private static boolean[] visited;

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int vertex = scanner.nextInt();
    int edge = scanner.nextInt();
    int start = scanner.nextInt();

    graph = new int[vertex + 1][vertex + 1];

    for (int i = 0; i < edge; i++) {
      addEdge(scanner.nextInt(), scanner.nextInt());
    }

    visited = new boolean[vertex + 1];

    dfs(start);

    System.out.println();

    visited = new boolean[vertex + 1];

    bfs(start);

  }

  public static void addEdge(int source, int destination) {
    graph[source][destination] = 1;
    graph[destination][source] = 1; // 양방향
  }

  public static void dfs(int start) {
    visited[start] = true;
    System.out.print(start + " ");

    for (int i = 1; i < graph.length; i++) {
      if (graph[start][i] == 1 && !visited[i]) {
        dfs(i);
      }
    }
  }

  public static void bfs(int start) {
    Queue<Integer> queue = new LinkedList<>();
    queue.offer(start);
    visited[start] = true;

    while (!queue.isEmpty()) {
      int current = queue.poll();
      System.out.print(current + " ");

      for (int i = 1; i < graph.length; i++) {
        if (graph[current][i] == 1 && !visited[i]) {
          queue.offer(i);
          visited[i] = true;
        }
      }

    }

  }
}
