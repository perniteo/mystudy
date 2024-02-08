package bitcamp.myapp.dao;

import bitcamp.myapp.vo.Assignment;
import java.util.List;

public interface AssignmentDao {

  void add(Assignment assignment);

  int delete(int key);

  List<Assignment> findAll();

  Assignment findBy(int key);

  int update(Assignment assignment);
}
