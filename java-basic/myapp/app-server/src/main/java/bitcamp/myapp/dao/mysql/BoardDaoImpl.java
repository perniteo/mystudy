package bitcamp.myapp.dao.mysql;

import bitcamp.myapp.dao.BoardDao;
import bitcamp.myapp.dao.DaoException;
import bitcamp.myapp.vo.Board;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BoardDaoImpl implements BoardDao {

  private final int category;

  Connection connection;

  public BoardDaoImpl(Connection connection, int category) {
    this.connection = connection;
    this.category = category;
  }

  @Override
  public void add(Board board) {
    try (PreparedStatement preparedStatement = connection.prepareStatement(
        "insert into boards(title, content, writer, category) "
            + "values(?, ?, ?, ?)"
    )) {
      preparedStatement.setString(1, board.getTitle());
      preparedStatement.setString(2, board.getContent());
      preparedStatement.setString(3, board.getWriter());
      preparedStatement.setInt(4, this.category);
//      Statement statement = connection.createStatement();
//      statement.executeUpdate(String.format(
//          "insert into "
//              + "boards(title, content, writer, category) "
//              + "values('%s', '%s', '%s', '%d')",
//          board.getTitle(), board.getContent(), board.getWriter(), this.category));
      preparedStatement.executeUpdate();
    } catch (Exception e) {
      throw new DaoException("Data Loading Error", e);
    }

  }

  @Override
  public int delete(int key) {
    try (PreparedStatement preparedStatement = connection.prepareStatement(
        "delete from boards where board_no = ?"
    )) {
      preparedStatement.setInt(1, key);

      return preparedStatement.executeUpdate();

    } catch (Exception e) {
      throw new DaoException("Data Loading Error", e);
    }
  }

  @Override
  public List<Board> findAll() {
    List<Board> boards = new ArrayList<>();
    try (PreparedStatement preparedStatement = connection.prepareStatement(
        "select * from boards where category = ? order by board_no desc"
    )) {
      preparedStatement.setInt(1, this.category);
      ResultSet resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        Board board = new Board();
        board.setNo(resultSet.getInt("board_no"));
        board.setTitle(resultSet.getString("title"));
        board.setContent(resultSet.getString("content"));
        board.setWriter(resultSet.getString("writer"));
        board.setCreatedDate(resultSet.getDate("created_date"));

        boards.add(board);
      }

    } catch (Exception e) {
      throw new DaoException("Data Loading Error", e);
    }
    return boards;
  }

  @Override
  public Board findBy(int key) {
    try (PreparedStatement preparedStatement = connection.prepareStatement(
        "select * from boards where board_no = ?"
    )) {
      preparedStatement.setInt(1, key);

      ResultSet resultSet = preparedStatement.getResultSet();

      if (resultSet.next()) {
        Board board = new Board();
        board.setNo(resultSet.getInt("board_no"));
        board.setTitle(resultSet.getString("title"));
        board.setContent(resultSet.getString("content"));
        board.setWriter(resultSet.getString("writer"));
        board.setCreatedDate(resultSet.getDate("created_date"));

        return board;
      }
    } catch (Exception e) {
      throw new DaoException("Data Loading Error", e);
    }
    return null;
  }

  @Override
  public int update(Board board) {
    try (PreparedStatement preparedStatement = connection.prepareStatement(
        "update boards set title = ?, content = ?, writer = ?"
            + "where board_no = ?"
    )) {
      preparedStatement.setString(1, board.getTitle());
      preparedStatement.setString(2, board.getContent());
      preparedStatement.setString(3, board.getWriter());
      preparedStatement.setInt(4, board.getNo());

      return preparedStatement.executeUpdate();

    } catch (Exception e) {
      throw new DaoException("Data Loading Error", e);
    }
  }
}
