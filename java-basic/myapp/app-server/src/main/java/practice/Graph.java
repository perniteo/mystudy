package practice;

import java.util.Iterator;
import java.util.LinkedList;

public class Graph {

  private int V; // 그래프의 정점 수
  private LinkedList<Integer>[] adj; // 인접 리스트

  Graph(int v) {
    V = v + 1;
    adj = new LinkedList[V];
    for (int i = 1; i < V; ++i) {
      adj[i] = new LinkedList<>();
    }
  }

  public static void main(String[] args) {

    Graph graph = new Graph(4);

    graph.addEdge(1, 2);
    graph.addEdge(1, 3);
    graph.addEdge(1, 4);
    graph.addEdge(2, 4);
    graph.addEdge(3, 4);
//    graph.addEdge(3, 3);

    System.out.println("Depth First Traversal starting from vertex 1:");

    graph.DFS(1);

//    System.out.println("Depth First Traversal starting from vertex 2:");
//
//    graph.DFS(2);
//    System.out.println();
//    System.out.println("Depth First Traversal starting from vertex 3:");
//
//    graph.DFS(3);
  }

  void addEdge(int v, int w) {
    adj[v].add(w);
    adj[w].add(v); // 양방향
//    LinkedList<Integer> lists = adj[v];
//    lists.add(w);
  }

  void DFSUtil(int v, boolean[] visited) {
    visited[v] = true;
    System.out.print(v + " ");

    Iterator<Integer> i = adj[v].listIterator();
    while (i.hasNext()) {
      int n = i.next();
      if (!visited[n]) {
        DFSUtil(n, visited);
      }
    }
  }

  void DFS(int v) {
    boolean[] visited = new boolean[V];
    DFSUtil(v, visited);
  }

}



