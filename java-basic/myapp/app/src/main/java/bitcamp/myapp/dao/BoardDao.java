package bitcamp.myapp.dao;

import bitcamp.myapp.vo.Board;
import java.util.List;

public class BoardDao extends AbstractDao<Board> {

  private int lastKey;

  public BoardDao(String filepath) {
    super(filepath);
    if (list.isEmpty()) {
      lastKey = 1;
    } else {
      lastKey = list.getLast().getNo();
    }
  }

  public void add(Board board) {
    board.setNo(++lastKey);
    this.list.add(board);
    saveData();
  }

  public int delete(int key) {
    int index = this.indexOf(key);
    if (index == -1) {
      System.out.println("Wrong input");
      return 0;
    }

    this.list.remove(index);
    saveData();
    return 1;
  }

  public List<Board> findAll() {
    return this.list.subList(0, list.size());
  }

  public Board findBy(int key) {
    int index = this.indexOf(key);
    if (index == -1) {
      return null;
    }
    return list.get(index);
  }

  public int update(Board board) {
    int index = indexOf(board.getNo());
    if (index == -1) {
      return 0;
    }
    list.set(index, board);
    saveData();
    return 1;
  }

  public int indexOf(int key) {
    for (int i = 0; i < list.size(); i++) {
      if (list.get(i).getNo() == key) {
        return i;
      }
    }
    return -1;
  }

}
