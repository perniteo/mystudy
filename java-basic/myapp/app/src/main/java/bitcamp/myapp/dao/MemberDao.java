package bitcamp.myapp.dao;

import bitcamp.myapp.vo.Member;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MemberDao {

  void add(Member member);

  int delete(int no);

  List<Member> findAll();

  Member findBy(int no);

  int update(Member member);

  Member findByEmailAndPassword(
      @Param("email") String email,
      @Param("password") String password);
}
