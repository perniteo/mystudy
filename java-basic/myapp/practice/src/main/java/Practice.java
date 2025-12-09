import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Practice {

  static int answer = Integer.MAX_VALUE;

  static String[] board;

  public static void countSpace(int startX, int startY) {
    int bStart = 0;
    int wStart = 0;

    for (int i = startX; i < startX + 8; i++) {
      for (int j = startY; j < startY + 8; j++) {
        if ((i + j) % 2 == 0) {
          if (board[i].charAt(j) == 'B') {
            wStart += 1;
          } else {
            bStart += 1;
          }
        } else {
          if (board[i].charAt(j) == 'B') {
            bStart += 1;
          } else {
            wStart += 1;
          }
        }
      }
    }
    System.out.println(wStart);
    System.out.println(bStart);
    answer = Math.min(answer, Math.min(bStart, wStart));
  }


  public static void main(String[] args) throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());

    int row = Integer.parseInt(st.nextToken());

    int column = Integer.parseInt(st.nextToken());

    board = new String[row];

    for (int i = 0; i < row; i++) {
      board[i] = br.readLine();
    }

    for (int i = 0; i <= row - 8; i++) {
      for (int j = 0; j <= column - 8; j++) {
        countSpace(i, j);
      }
    }
    System.out.println(answer);

  }
}