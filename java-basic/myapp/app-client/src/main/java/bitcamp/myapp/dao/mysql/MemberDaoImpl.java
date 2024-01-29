package bitcamp.myapp.dao.mysql;

import bitcamp.myapp.dao.DaoException;
import bitcamp.myapp.dao.MemberDao;
import bitcamp.myapp.vo.Member;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MemberDaoImpl implements MemberDao {

  Connection connection;

  public MemberDaoImpl(Connection connection) {
    this.connection = connection;
  }

  @Override
  public void add(Member member) {

    try (Statement statement = connection.createStatement()) {
      statement.executeUpdate(String.format("insert into "
              + "members(email, name, password) "
              + "values('%s', '%s', sha2('%s', 256))", member.getEmail(), member.getName(),
          member.getPassword()));
    } catch (Exception e) {
      throw new DaoException("Data loading error", e);
    }

  }

  @Override
  public int delete(int key) {

    try (Statement statement = connection.createStatement()) {
      return statement.executeUpdate("delete from members " +
          "where member_no = " + key);

    } catch (Exception e) {
      throw new DaoException("Data loading Error", e);
    }
  }

  @Override
  public int update(Member member) {

    try (Statement statement = connection.createStatement()) {
      return statement.executeUpdate(String.format("update members " +
              "set email = '%s', name = '%s', password = sha2('%s', 256) " +
              "where member_no = %d", member.getEmail(), member.getName(), member.getPassword(),
          member.getNo()));

    } catch (Exception e) {
      throw new DaoException("Data loading err", e);
    }
  }

  @Override
  public List<Member> findAll() {

    List<Member> members = new ArrayList<>();

    try (Statement statement = connection.createStatement()) {
      ResultSet resultSet = statement.executeQuery("select * "
          + "from members");

      while (resultSet.next()) {
        Member member = new Member();
        member.setNo(resultSet.getInt("member_no"));
        member.setEmail(resultSet.getString("email"));
        member.setName("name");
        member.setPassword(resultSet.getString("password"));
        member.setCreatedDate(resultSet.getDate("join_date"));

        members.add(member);
      }

      return members;

    } catch (Exception e) {
      throw new DaoException("Data loading error", e);
    }
  }

  @Override
  public Member findBy(int key) {

    try (Statement statement = connection.createStatement()) {
      ResultSet resultSet = statement.executeQuery("select * " +
          "from members " +
          "where member_no = " + key);

      if (resultSet.next()) {
        Member member = new Member();
        member.setNo(resultSet.getInt("member_no"));
        member.setEmail(resultSet.getString("email"));
        member.setName(resultSet.getString("name"));
        member.setPassword(resultSet.getString("password"));
        member.setCreatedDate(resultSet.getDate("join_date"));

        return member;
      }
    } catch (Exception e) {
      throw new DaoException("data Loading err", e);
    }
    return null;
  }
}
