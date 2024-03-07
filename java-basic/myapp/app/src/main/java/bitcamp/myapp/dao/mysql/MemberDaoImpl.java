package bitcamp.myapp.dao.mysql;

import bitcamp.myapp.dao.DaoException;
import bitcamp.myapp.dao.MemberDao;
import bitcamp.myapp.vo.Member;
import bitcamp.util.Component;
import bitcamp.util.DBConnectionPool;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Component
public class MemberDaoImpl implements MemberDao {

  DBConnectionPool dbConnectionPool;

  public MemberDaoImpl(DBConnectionPool dbConnectionPool) {
    this.dbConnectionPool = dbConnectionPool;
  }

  @Override
  public void add(Member member) {

    try (Connection connection = dbConnectionPool.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(
            "insert into members(email, name, password, photo)"
                + "values(?, ?, sha2(?, 256), ?)"
        )) {
      preparedStatement.setString(1, member.getEmail());
      preparedStatement.setString(2, member.getName());
      preparedStatement.setString(3, member.getPassword());
      preparedStatement.setString(4, member.getPhoto());

      preparedStatement.executeUpdate();
    } catch (Exception e) {
      throw new DaoException("Data loading error", e);
    }

  }

  @Override
  public int delete(int key) {

    try (Connection connection = dbConnectionPool.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(
            "delete from members where member_no = ?"
        )) {
      preparedStatement.setInt(1, key);

      return preparedStatement.executeUpdate();

    } catch (Exception e) {
      throw new DaoException("Data loading Error", e);
    }
  }

  @Override
  public int update(Member member) {

    try (Connection connection = dbConnectionPool.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(
            "update members set email = ?, name = ?, password = sha2(?, 256), photo = ? "
                + "where member_no = ?"
        )) {
      preparedStatement.setString(1, member.getEmail());
      preparedStatement.setString(2, member.getName());
      preparedStatement.setString(3, member.getPassword());
      preparedStatement.setString(4, member.getPhoto());
      preparedStatement.setInt(5, member.getNo());

      return preparedStatement.executeUpdate();

    } catch (Exception e) {
      throw new DaoException("Data loading err", e);
    }
  }

  @Override
  public List<Member> findAll() {

    List<Member> members = new ArrayList<>();

    try (Connection connection = dbConnectionPool.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(
            "select * from members order by member_no desc"
        )) {
      ResultSet resultSet = preparedStatement.executeQuery();

      while (resultSet.next()) {
        Member member = new Member();
        member.setNo(resultSet.getInt("member_no"));
        member.setEmail(resultSet.getString("email"));
        member.setName(resultSet.getString("name"));
        member.setPassword(resultSet.getString("password"));
        member.setPhoto(resultSet.getString("photo"));
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

    try (Connection connection = dbConnectionPool.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(
            "select * from members where member_no = ?"
        )) {
      preparedStatement.setInt(1, key);

      ResultSet resultSet = preparedStatement.executeQuery();

      if (resultSet.next()) {
        Member member = new Member();
        member.setNo(resultSet.getInt("member_no"));
        member.setEmail(resultSet.getString("email"));
        member.setName(resultSet.getString("name"));
        member.setPassword(resultSet.getString("password"));
        member.setPhoto(resultSet.getString("photo"));
        member.setCreatedDate(resultSet.getDate("join_date"));

        return member;
      }
    } catch (Exception e) {
      throw new DaoException("data Loading err", e);
    }
    return null;
  }

  @Override
  public Member findByEmailAndPassword(String email, String password) {

    try (Connection connection = dbConnectionPool.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(
            "select member_no, email, name, join_date from members "
                + "where email = ? and password = sha2(?, 256)"
        )) {
      preparedStatement.setString(1, email);
      preparedStatement.setString(2, password);

      ResultSet rs = preparedStatement.executeQuery();

      if (rs.next()) {
        Member member = new Member();
        member.setNo(rs.getInt("member_no"));
        member.setEmail(rs.getString("email"));
        member.setName(rs.getString("name"));
        member.setCreatedDate(rs.getDate("join_date"));
        return member;
      }

    } catch (Exception e) {
      throw new DaoException("error");
    }
    return null;
  }
}
