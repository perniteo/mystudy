package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//int[][] 2중 배열을 사용해서 양방향 그래프를 나타내었으나 0 or 1값을 포함하는 boolean 값만 대입하게 되므로
//boolean[][] 로 교체, 또한 edge 추가하는 과정에서 같은 주소값을 할당하는 게 나을 듯 함
//이것만 했을 땐 성능이 똑같았음 boolean의 값이 내부적으로 int메모리를 사용해서 그런듯함

//2안 ) bufferedReader와 StringTokenizer를 사용
//눈에 띄는 성능개선이 이루어졌음, 데이터 입출력 양이 많지도 않았는데 이러한 차이가 나는 이유는 모르겠음
//bufferedReader는 내부 버퍼를 사용하는 것을 알고 있었지만, Scanner는 입력마다

public class Virus {

  static int answer;
  static boolean[][] graph;
  static boolean[] visited;

  static void addEdge(int source, int destination) {
    graph[source][destination] = graph[destination][source] = true;
  }

  static void bfs(int start) {
    Queue<Integer> queue = new LinkedList<>();
    visited[start] = true;

    queue.offer(start);

    while (!queue.isEmpty()) {
      int now = queue.poll();

      for (int i = 1; i < graph.length; i++) {
        if (graph[now][i] && !visited[i]) {
          queue.offer(i);
          visited[i] = true;
          ++answer;
        }
      }
    }

  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int vertex = Integer.parseInt(br.readLine());
    graph = new boolean[vertex + 1][vertex + 1];
    visited = new boolean[vertex + 1];

    int edge = Integer.parseInt(br.readLine());

    for (int i = 0; i < edge; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());

      int s = Integer.parseInt(st.nextToken());
      int d = Integer.parseInt(st.nextToken());
      addEdge(s, d);
    }
    answer = 0;

    bfs(1);

    System.out.println(answer);

  }
}