package bitcamp.myapp.dao.mysql;

import bitcamp.myapp.dao.DaoException;
import bitcamp.myapp.dao.MemberDao;
import bitcamp.myapp.vo.Member;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MemberDaoImpl implements MemberDao {

  Connection connection;

  public MemberDaoImpl(Connection connection) {
    this.connection = connection;
  }

  @Override
  public void add(Member member) {

    try (PreparedStatement preparedStatement = connection.prepareStatement(
        "insert into members(email, name, password)"
            + "values(?, ?, sha2(?, 256))"
    )) {
      preparedStatement.setString(1, member.getEmail());
      preparedStatement.setString(2, member.getName());
      preparedStatement.setString(3, member.getPassword());

      preparedStatement.executeUpdate();
    } catch (Exception e) {
      throw new DaoException("Data loading error", e);
    }

  }

  @Override
  public int delete(int key) {

    try (PreparedStatement preparedStatement = connection.prepareStatement(
        "delete from member where member_no = ?"
    )) {
      preparedStatement.setInt(1, key);

      return preparedStatement.executeUpdate();

    } catch (Exception e) {
      throw new DaoException("Data loading Error", e);
    }
  }

  @Override
  public int update(Member member) {

    try (PreparedStatement preparedStatement = connection.prepareStatement(
        "update members set email = ?, name = ?, password = sha2(?, 256)"
            + "where member_no = ?"
    )) {
      preparedStatement.setString(1, member.getEmail());
      preparedStatement.setString(2, member.getName());
      preparedStatement.setString(3, member.getPassword());
      preparedStatement.setInt(4, member.getNo());

      return preparedStatement.executeUpdate();

    } catch (Exception e) {
      throw new DaoException("Data loading err", e);
    }
  }

  @Override
  public List<Member> findAll() {

    List<Member> members = new ArrayList<>();

    try (PreparedStatement preparedStatement = connection.prepareStatement(
        "select * from members order by member_no desc"
    )) {
      ResultSet resultSet = preparedStatement.executeQuery();

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

    try (PreparedStatement preparedStatement = connection.prepareStatement(
        "select * from where member_no = ?"
    )) {
      preparedStatement.setInt(1, key);

      ResultSet resultSet = preparedStatement.executeQuery();

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
