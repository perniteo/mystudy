package bitcamp.myapp.dao;

import bitcamp.myapp.vo.Assignment;
import java.util.List;

public class AssignmentDao extends AbstractDao<Assignment> {

  private int lastKey;

  public AssignmentDao(String filepath) {
    super(filepath);

    if (list.isEmpty()) {
      lastKey = 1;
    } else {
      lastKey = this.list.getLast().getNo();
    }
  }

  public void add(Assignment assignment) {
    assignment.setNo(++lastKey);
    this.list.add(assignment);
    saveData();
  }

  public int delete(int key) {
    int index = indexOf(key);
    if (index == -1) {
      System.out.println("wrong input");
      return 0;
    }
    this.list.remove(index);
    saveData();
    return 1;
  }

  public List<Assignment> findAll() {
    return this.list.subList(0, list.size());
  }

  public Assignment findBy(int key) {
    int index = indexOf(key);
    if (index == -1) {
      System.out.println("Wrong input");
      return null;
    }
    return this.list.get(index);
  }

  public int update(Assignment assignment) {
    int index = indexOf(assignment.getNo());
    if (index == -1) {
      System.out.println("Wrong input");
      return 0;
    }
    this.list.set(index, assignment);
    saveData();
    return 1;
  }

  public int indexOf(int key) {
    for (int i = 0; i < this.list.size(); i++) {
      if (list.get(i).getNo() == key) {
        return i;
      }
    }
    return -1;
  }
}
