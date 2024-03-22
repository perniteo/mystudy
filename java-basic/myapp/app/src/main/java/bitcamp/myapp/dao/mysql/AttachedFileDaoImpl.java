package bitcamp.myapp.dao.mysql;

import bitcamp.myapp.dao.AttachedFileDao;
import bitcamp.myapp.vo.AttachedFile;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Component;

@Component
public class AttachedFileDaoImpl implements AttachedFileDao {

  private final Log log = LogFactory.getLog(this.getClass());
  SqlSessionFactory sqlSessionFactory;

  public AttachedFileDaoImpl(
      SqlSessionFactory sqlSessionFactory) {
    log.debug("AttachedFileDaoImpl 생성자");
    this.sqlSessionFactory = sqlSessionFactory;
  }


  @Override
  public void add(AttachedFile file) {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()
//        Connection connection = dbConnectionPool.getConnection();
//        PreparedStatement preparedStatement = connection.prepareStatement(
//            "insert into board_files(file_path, board_no) "
//                + "values(?, ?)"
//        )
    ) {
//      preparedStatement.setString(1, file.getFilePath());
//
//      preparedStatement.setInt(2, file.getBoardNo());
//
//      preparedStatement.executeUpdate();
      sqlSession.insert("AttachedFileDao.add", file);
    }
//    catch (Exception e) {
//      throw new DaoException("Data Loading Error", e);
//    }
  }

  @Override
  public int addAll(List<AttachedFile> files) {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()
//        Connection connection = dbConnectionPool.getConnection();
//        PreparedStatement preparedStatement = connection.prepareStatement(
//            "insert into board_files(file_path, board_no) "
//                + "values(?, ?)"
//        )
    ) {

//      for (AttachedFile file : files) {
//        preparedStatement.setString(1, file.getFilePath());
//        preparedStatement.setInt(2, file.getBoardNo());
//
//        preparedStatement.executeUpdate();
//      }

//      return files.size();
      return sqlSession.insert("AttachedFileDao.addAll", files);
    }
//    catch (Exception e) {
//      throw new DaoException("Data Loading Error", e);
//    }
  }

  @Override
  public int delete(int fileNo) {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()
//        Connection connection = dbConnectionPool.getConnection();
//        PreparedStatement preparedStatement = connection.prepareStatement(
//            "delete from board_files where file_no = ?"
//        )
    ) {
//      preparedStatement.setInt(1, fileNo);
//
//      return preparedStatement.executeUpdate();
      return sqlSession.delete("AttachedFileDao.delete", fileNo);

    }
//    catch (Exception e) {
//      throw new DaoException("Data Loading Error", e);
//    }
  }

  @Override
  public int deleteAll(int boardNo) {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()
//        Connection connection = dbConnectionPool.getConnection();
//        PreparedStatement preparedStatement = connection.prepareStatement(
//            "delete from board_files where board_no = ?"
//        )
    ) {
//      preparedStatement.setInt(1, boardNo);
//
//      return preparedStatement.executeUpdate();

      return sqlSession.delete("AttachedFileDao.deleteAll", boardNo);

    }
//    catch (Exception e) {
//      throw new DaoException("Data Loading Error", e);
//    }
  }

  @Override
  public List<AttachedFile> findAllByBoardNo(int boardNo) {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()
//        Connection connection = dbConnectionPool.getConnection();
//        PreparedStatement preparedStatement = connection.prepareStatement(
//            "select file_no, file_path, board_no "
//                + "from board_files where board_no = ? order by file_no asc"
//        )
    ) {
      return sqlSession.selectList("AttachedFileDao.findAllByBoardNo", boardNo);
//      preparedStatement.setInt(1, boardNo);
//
//      List<AttachedFile> files = new ArrayList<>();
//
//      ResultSet resultSet = preparedStatement.executeQuery();
//      while (resultSet.next()) {
//        AttachedFile file = new AttachedFile();
//        file.setNo(resultSet.getInt("file_no"));
//        file.setFilePath(resultSet.getString("file_path"));
//        file.setBoardNo(resultSet.getInt("board_no"));
//
//        files.add(file);
//      }
//      return files;

    }
//    catch (Exception e) {
//      throw new DaoException("Data Loading Error", e);
//    }
  }

  @Override
  public AttachedFile findByNo(int no) {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()
//        Connection connection = dbConnectionPool.getConnection();
//        PreparedStatement preparedStatement = connection.prepareStatement(
//            "select file_no, file_path, board_no "
//                + "from board_files where file_no = ?"
//        )
    ) {
//      preparedStatement.setInt(1, no);
//
//      ResultSet resultSet = preparedStatement.executeQuery();
//
//      if (resultSet.next()) {
//        AttachedFile file = new AttachedFile();
//        file.setNo(resultSet.getInt("file_no"));
//        file.setFilePath(resultSet.getString("file_path"));
//        file.setBoardNo(resultSet.getInt("board_no"));
//
//        return file;
//      }
//      return null;
      return sqlSession.selectOne("AttachedFileDao.findByNo", no);
    }
//    catch (Exception e) {
//      throw new DaoException("Data Loading Error", e);
//    }
  }
}
