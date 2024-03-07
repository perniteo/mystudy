package bitcamp.myapp.dao.mysql;

import bitcamp.myapp.dao.AssignmentDao;
import bitcamp.myapp.dao.DaoException;
import bitcamp.myapp.vo.Assignment;
import bitcamp.util.DBConnectionPool;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class AssignmentDaoImpl implements AssignmentDao {

  DBConnectionPool dbConnectionPool;

  public AssignmentDaoImpl(DBConnectionPool dbConnectionPool) {
    System.out.println("AssignmentDao");
    this.dbConnectionPool = dbConnectionPool;
  }

  @Override
  public void add(Assignment assignment) {
    try (Connection connection = dbConnectionPool.getConnection();
        PreparedStatement preparedstatement = connection.prepareStatement(
            "insert into assignments(title, content, deadline) "
                + "values(?, ?, ?)")) {

      preparedstatement.setString(1, assignment.getTitle());
      preparedstatement.setString(2, assignment.getContent());
      preparedstatement.setDate(3, assignment.getDeadline());

      preparedstatement.executeUpdate();

    } catch (Exception e) {
      throw new DaoException("Data Loading Error", e);
    }
  }


  @Override
  public int delete(int key) {
    try (Connection connection = dbConnectionPool.getConnection();
        PreparedStatement preparedstatement = connection.prepareStatement(
            "delete from assignments where assignment_no = ?"
        )) {
      preparedstatement.setInt(1, key);
      return preparedstatement.executeUpdate();
    } catch (Exception e) {
      throw new DaoException("Data Loading Error", e);
    }
  }

  @Override
  public List<Assignment> findAll() {

    List<Assignment> assignments = new ArrayList<>();

    try (Connection connection = dbConnectionPool.getConnection();
        PreparedStatement preparedstatement = connection.prepareStatement(
            "select * from assignments order by assignment_no desc"
        )) {
      ResultSet resultSet = preparedstatement.executeQuery();
      while (resultSet.next()) {
        Assignment assignment = new Assignment();
        assignment.setNo(resultSet.getInt("assignment_no"));
        assignment.setTitle(resultSet.getString("title"));
        assignment.setContent(resultSet.getString("content"));
        assignment.setDeadline(resultSet.getDate("deadline"));

        assignments.add(assignment);
      }

    } catch (Exception e) {
      throw new DaoException("Data Loading Error", e);
    }
    return assignments;
  }

  @Override
  public Assignment findBy(int key) {
    try (Connection connection = dbConnectionPool.getConnection();
        PreparedStatement preparedstatement = connection.prepareStatement(
            "select * from assignments where assignment_no = ? "
        )) {
      preparedstatement.setInt(1, key);
      ResultSet resultSet = preparedstatement.executeQuery();
      if (resultSet.next()) {
        Assignment assignment = new Assignment();
        assignment.setNo(resultSet.getInt("assignment_no"));
        assignment.setTitle(resultSet.getString("title"));
        assignment.setContent(resultSet.getString("content"));
        assignment.setDeadline(resultSet.getDate("deadline"));

        return assignment;
      }

    } catch (Exception e) {
      throw new DaoException("Data Loading Error", e);
    }
    return null;
  }

  @Override
  public int update(Assignment assignment) {
    try (Connection connection = dbConnectionPool.getConnection();
        PreparedStatement preparedstatement = connection.prepareStatement(
            "update assignments set title = ?, content = ?, deadline = ? "
                + "where assignment_no = ?"
        )) {
      preparedstatement.setString(1, assignment.getTitle());
      preparedstatement.setString(2, assignment.getContent());
      preparedstatement.setDate(3, assignment.getDeadline());
      preparedstatement.setInt(4, assignment.getNo());

      return preparedstatement.executeUpdate();
    } catch (Exception e) {
      throw new DaoException("Data Loading Error", e);
    }
  }
}
