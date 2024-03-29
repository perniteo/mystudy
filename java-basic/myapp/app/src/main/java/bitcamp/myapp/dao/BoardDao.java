package bitcamp.myapp.dao;

import bitcamp.myapp.vo.Board;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BoardDao {

  void add(Board board);

  int delete(int key);

  List<Board> findAll(int category);

  Board findBy(int key);

  int update(Board board);
}
