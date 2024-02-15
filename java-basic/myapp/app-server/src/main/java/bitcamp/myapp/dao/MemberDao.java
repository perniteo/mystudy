package bitcamp.myapp.dao;

import bitcamp.myapp.vo.Member;
import java.util.List;

public interface MemberDao {

  void add(Member member);

  int delete(int key);

  int update(Member member);

  List<Member> findAll();

  Member findBy(int key);

  Member findByEmailAndPassword(String email, String password);
}
