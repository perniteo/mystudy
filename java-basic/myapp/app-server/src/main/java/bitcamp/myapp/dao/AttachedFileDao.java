package bitcamp.myapp.dao;

import bitcamp.myapp.vo.AttachedFile;
import java.util.List;

public interface AttachedFileDao {

  void add(AttachedFile file);

  int addAll(List<AttachedFile> files);

  int delete(int fileNo);

  int deleteAll(int boardNo);

  List<AttachedFile> findAllByBoardNo(int boardNo);
}
