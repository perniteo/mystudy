package bitcamp.myapp.dao.mysql;

import bitcamp.myapp.dao.BoardDao;
import bitcamp.myapp.dao.DaoException;
import bitcamp.myapp.vo.Board;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
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
    try {
      Statement statement = connection.createStatement();
      statement.executeUpdate(String.format(
          "insert into "
              + "boards(title, content, writer) "
              + "values('%s', '%s', '%s')",
          board.getTitle(), board.getContent(), board.getWriter()));
    } catch (Exception e) {
      throw new DaoException("Data Loading Error", e);
    }

  }

  @Override
  public int delete(int key) {
    try {
      Statement statement = connection.createStatement();
      return statement.executeUpdate("delete from boards "
          + "where board_no = " + key);
    } catch (Exception e) {
      throw new DaoException("Data Loading Error", e);
    }
  }

  @Override
  public List<Board> findAll() {
    List<Board> boards = new ArrayList<>();
    try {
      Statement statement = connection.createStatement();
      ResultSet resultSet = statement.executeQuery(
          "select * "
              + "from boards");
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
    try {
      Statement statement = connection.createStatement();
      ResultSet resultSet = statement.executeQuery(
          "select * "
              + "from boards "
              + "where board_no = " + key);
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
    try {
      Statement statement = connection.createStatement();
      return statement.executeUpdate(String.format(
          "update boards "
              + "set title = '%s', content = '%s', writer = '%s' "
              + "where board_no = %d",
          board.getTitle(), board.getContent(), board.getWriter(), board.getNo()));
    } catch (Exception e) {
      throw new DaoException("Data Loading Error", e);
    }
  }
}
