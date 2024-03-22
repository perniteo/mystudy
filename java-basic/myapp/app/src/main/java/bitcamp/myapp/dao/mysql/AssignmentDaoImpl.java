package bitcamp.myapp.dao.mysql;

import bitcamp.myapp.dao.AssignmentDao;
import bitcamp.myapp.vo.Assignment;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class AssignmentDaoImpl implements AssignmentDao {

  private final Log log = LogFactory.getLog(this.getClass());
  SqlSessionFactory sqlSessionFactory;

  public AssignmentDaoImpl(SqlSessionFactory sqlSessionFactory) {
    log.debug("AssignmentDaoImpl 생성자");
    this.sqlSessionFactory = sqlSessionFactory;
  }

  @Override
  public void add(Assignment assignment) {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()
//        Connection connection = dbConnectionPool.getConnection();
//        PreparedStatement preparedstatement = connection.prepareStatement(
//            "insert into assignments(title, content, deadline) "
//                + "values(?, ?, ?)")
    ) {

//      preparedstatement.setString(1, assignment.getTitle());
//      preparedstatement.setString(2, assignment.getContent());
//      preparedstatement.setDate(3, assignment.getDeadline());
//
//      preparedstatement.executeUpdate();
      sqlSession.insert("AssignmentDao.add", assignment);

    }
//    catch (Exception e) {
//      throw new DaoException("Data Loading Error", e);
//    }
  }


  @Override
  public int delete(int key) {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()
//        Connection connection = dbConnectionPool.getConnection();
//        PreparedStatement preparedstatement = connection.prepareStatement(
//            "delete from assignments where assignment_no = ?"
//        )
    ) {
//      preparedstatement.setInt(1, key);
//      return preparedstatement.executeUpdate();
      return sqlSession.delete("AssignmentDao.delete", key);
    }
//    catch (Exception e) {
//      throw new DaoException("Data Loading Error", e);
//    }
  }

  @Override
  public List<Assignment> findAll() {

//    List<Assignment> assignments = new ArrayList<>();

    try (SqlSession sqlSession = sqlSessionFactory.openSession()
//        Connection connection = dbConnectionPool.getConnection();
//        PreparedStatement preparedstatement = connection.prepareStatement(
//            "select * from assignments order by assignment_no desc"
//        )
    ) {
//      ResultSet resultSet = preparedstatement.executeQuery();
//      while (resultSet.next()) {
//        Assignment assignment = new Assignment();
//        assignment.setNo(resultSet.getInt("assignment_no"));
//        assignment.setTitle(resultSet.getString("title"));
//        assignment.setContent(resultSet.getString("content"));
//        assignment.setDeadline(resultSet.getDate("deadline"));
//
//        assignments.add(assignment);
//      }
      return sqlSession.selectList("AssignmentDao.findAll");
    }
//    catch (Exception e) {
//      throw new DaoException("Data Loading Error", e);
//    }
//    return assignments;
  }

  @Override
  public Assignment findBy(int key) {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()
//        Connection connection = dbConnectionPool.getConnection();
//        PreparedStatement preparedstatement = connection.prepareStatement(
//            "select * from assignments where assignment_no = ? "
//        )
    ) {
//      preparedstatement.setInt(1, key);
//      ResultSet resultSet = preparedstatement.executeQuery();
//      if (resultSet.next()) {
//        Assignment assignment = new Assignment();
//        assignment.setNo(resultSet.getInt("assignment_no"));
//        assignment.setTitle(resultSet.getString("title"));
//        assignment.setContent(resultSet.getString("content"));
//        assignment.setDeadline(resultSet.getDate("deadline"));
//
//        return assignment;
//      }
      return sqlSession.selectOne("AssignmentDao.findBy", key);
    }
//    catch (Exception e) {
//      throw new DaoException("Data Loading Error", e);
//    }
//    return null;
  }

  @Override
  public int update(Assignment assignment) {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()
//        Connection connection = dbConnectionPool.getConnection();
//        PreparedStatement preparedstatement = connection.prepareStatement(
//            "update assignments set title = ?, content = ?, deadline = ? "
//                + "where assignment_no = ?"
//        )
    ) {
//      preparedstatement.setString(1, assignment.getTitle());
//      preparedstatement.setString(2, assignment.getContent());
//      preparedstatement.setDate(3, assignment.getDeadline());
//      preparedstatement.setInt(4, assignment.getNo());
//
//      return preparedstatement.executeUpdate();
      return sqlSession.update("AssignmentDao.update", assignment);
    }
//    catch (Exception e) {
//      throw new DaoException("Data Loading Error", e);
//    }
  }
}
