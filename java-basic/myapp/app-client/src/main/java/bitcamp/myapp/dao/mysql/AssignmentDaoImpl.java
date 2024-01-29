package bitcamp.myapp.dao.mysql;

import bitcamp.myapp.dao.AssignmentDao;
import bitcamp.myapp.dao.DaoException;
import bitcamp.myapp.vo.Assignment;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AssignmentDaoImpl implements AssignmentDao {

  Connection connection;

  public AssignmentDaoImpl(Connection connection) {
    this.connection = connection;
  }

  @Override
  public void add(Assignment assignment) {
    try (Statement statement = connection.createStatement()) {
      statement.executeUpdate(String.format("insert into "
              + "assignments(title, content, deadline) "
              + "values('%s', '%s', '%s')"
          , assignment.getTitle(), assignment.getContent(), assignment.getDeadline()));
    } catch (Exception e) {
      throw new DaoException("Data Loading Error", e);
    }
  }


  @Override
  public int delete(int key) {
    try (Statement statement = connection.createStatement()) {
      return statement.executeUpdate("delete from assignments "
          + "where assignment_no = " + key);
    } catch (Exception e) {
      throw new DaoException("Data Loading Error", e);
    }
  }

  @Override
  public List<Assignment> findAll() {

    List<Assignment> assignments = new ArrayList<>();

    try (Statement statement = connection.createStatement()) {
      ResultSet resultSet = statement.executeQuery("select * "
          + "from assignments");
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
    try (Statement statement = connection.createStatement()) {
      ResultSet resultSet = statement.executeQuery("select * "
          + "from assignments "
          + "where assignment_no = " + key);
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

    try (Statement statement = connection.createStatement()) {
      return statement.executeUpdate(String.format("update assignments " +
              "set title = '%s', content = '%s', deadline = '%s' " +
              "where assignment_no = '%d'",
          assignment.getTitle(), assignment.getContent(), assignment.getDeadline(),
          assignment.getNo()));
    } catch (Exception e) {
      throw new DaoException("Data Loading Error", e);
    }

  }
}
