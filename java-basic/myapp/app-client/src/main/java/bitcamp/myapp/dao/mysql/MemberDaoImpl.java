package bitcamp.myapp.dao.mysql;

import bitcamp.myapp.dao.MemberDao;
import bitcamp.myapp.vo.Member;
import java.sql.Connection;
import java.util.List;

public class MemberDaoImpl implements MemberDao {

  Connection connection;

  public MemberDaoImpl(Connection connection) {
    this.connection = connection;
  }

  @Override
  public void add(Member member) {

  }

  @Override
  public int delete(int key) {
    return 0;
  }

  @Override
  public int update(Member member) {
    return 0;
  }

  @Override
  public List<Member> findAll() {
    return null;
  }

  @Override
  public Member findBy(int key) {
    return null;
  }
}
