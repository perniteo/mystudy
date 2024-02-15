package bitcamp.myapp.dao.mysql;

import bitcamp.myapp.dao.AttachedFileDao;
import bitcamp.myapp.dao.DaoException;
import bitcamp.myapp.vo.AttachedFile;
import bitcamp.util.DBConnectionPool;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AttachedFileDaoImpl implements AttachedFileDao {

  DBConnectionPool dbConnectionPool;

  public AttachedFileDaoImpl(DBConnectionPool dbConnectionPool) {
    this.dbConnectionPool = dbConnectionPool;
  }

  @Override
  public void add(AttachedFile file) {
    try (Connection connection = dbConnectionPool.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(
            "insert into board_files(file_path, board_no) "
                + "values(?, ?)"
        )) {
      preparedStatement.setString(1, file.getFilePath());

      preparedStatement.setInt(2, file.getBoardNo());

      preparedStatement.executeUpdate();
    } catch (Exception e) {
      throw new DaoException("Data Loading Error", e);
    }
  }

  @Override
  public int addAll(List<AttachedFile> files) {
    try (Connection connection = dbConnectionPool.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(
            "insert into board_files(file_path, board_no) "
                + "values(?, ?)"
        )) {

      for (AttachedFile file : files) {
        preparedStatement.setString(1, file.getFilePath());
        preparedStatement.setInt(2, file.getBoardNo());

        preparedStatement.executeUpdate();
      }

      return files.size();

    } catch (Exception e) {
      throw new DaoException("Data Loading Error", e);
    }
  }

  @Override
  public int delete(int fileNo) {
    try (Connection connection = dbConnectionPool.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(
            "delete from board_files where file_no = ?"
        )) {
      preparedStatement.setInt(1, fileNo);

      return preparedStatement.executeUpdate();

    } catch (Exception e) {
      throw new DaoException("Data Loading Error", e);
    }
  }

  @Override
  public int deleteAll(int boardNo) {
    try (Connection connection = dbConnectionPool.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(
            "delete from board_files where board_no = ?"
        )) {
      preparedStatement.setInt(1, boardNo);

      return preparedStatement.executeUpdate();

    } catch (Exception e) {
      throw new DaoException("Data Loading Error", e);
    }
  }

  @Override
  public List<AttachedFile> findAllByBoardNo(int boardNo) {
    try (Connection connection = dbConnectionPool.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(
            "select file_no, file_path, board_no "
                + "from board_files where board_no = ? order by file_no asc"
        )) {
      preparedStatement.setInt(1, boardNo);

      List<AttachedFile> files = new ArrayList<>();

      ResultSet resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        AttachedFile file = new AttachedFile();
        file.setNo(resultSet.getInt("file_no"));
        file.setFilePath(resultSet.getString("file_path"));
        file.setBoardNo(resultSet.getInt("board_no"));

        files.add(file);
      }
      return files;
      
    } catch (Exception e) {
      throw new DaoException("Data Loading Error", e);
    }
  }
}
