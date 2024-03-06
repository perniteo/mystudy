package practice.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class Graph2 {

  private final Map<Integer, List<Integer>> graph;

  public Graph2() {
    graph = new HashMap<>();
  }

  public static void main(String[] args) {
    Graph2 graph2 = new Graph2();

    graph2.addEdge(0, 1);
    graph2.addEdge(0, 2);
    graph2.addEdge(1, 3);
    graph2.addEdge(1, 4);
    graph2.addEdge(2, 5);

    graph2.bfs(0);
  }

  public void addEdge(int source, int destination) {
    graph.putIfAbsent(source, new ArrayList<>());
    graph.putIfAbsent(destination, new ArrayList<>());
    graph.get(source).add(destination);
    graph.get(destination).add(source); // 양방향 그래프
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
