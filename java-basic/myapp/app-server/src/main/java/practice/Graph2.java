package practice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;

public class Graph2 {

  private static List<Integer>[] graph1;
  private final Map<Integer, List<Integer>> graph;

  public Graph2() {
    graph = new HashMap<>();
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    StringTokenizer st = new StringTokenizer(scanner.nextLine());
    int vertex = Integer.parseInt(st.nextToken());
    int edge = Integer.parseInt(st.nextToken());
    int start = Integer.parseInt(st.nextToken());

    graph1 = new ArrayList[vertex + 1];
    for (int i = 1; i < vertex; i++) {
      graph1[i] = new ArrayList<>();
    }
    Graph2 graph2 = new Graph2();
    for (int i = 0; i < edge; i++) {
      StringTokenizer st2 = new StringTokenizer(scanner.nextLine());
      int a = Integer.parseInt(st2.nextToken());
      int b = Integer.parseInt(st2.nextToken());
      graph1[a].add(b);
      graph1[b].add(a);
      graph2.addEdge(a, b);
    }
    for (int i = 1; i < vertex + 1; i++) {
      Collections.sort(graph1[i]);
    }

    for (Entry entry : graph2.graph.entrySet()) {
      Collections.sort((List<Integer>) entry.getValue());
    }
    graph2.dfs(start);
    System.out.println();
    graph2.bfs(start);
  }

  public void addEdge(int source, int destination) {
    graph.putIfAbsent(source, new ArrayList<>());
    graph.putIfAbsent(destination, new ArrayList<>());
    graph.get(source).add(destination);
    graph.get(destination).add(source); // 양방향 그래프
  }

  public void dfs(int start) {
    Set<Integer> visited = new HashSet<>();
    dfsUtil(start, visited);
  }

  public void dfsUtil(int vertex, Set<Integer> visited) {
    visited.add(vertex);
    System.out.print(vertex + " ");

    for (int neighbor : graph.getOrDefault(vertex, new ArrayList<>())) {
      if (!visited.contains(neighbor)) {
        dfsUtil(neighbor, visited);
      }
    }
  }

  public void bfs(int start) {
    Set<Integer> visited = new HashSet<>();
    Queue<Integer> queue = new LinkedList<>();

    visited.add(start);
    queue.offer(start);

    while (!queue.isEmpty()) {
      int current = queue.poll();
      System.out.print(current + " ");

      for (int neighbor : graph.getOrDefault(current, new ArrayList<>())) {
        if (!visited.contains(neighbor)) {
          visited.add(neighbor);
          queue.offer(neighbor);
        }
      }
    }
  }
}
