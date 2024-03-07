package bitcamp.myapp.dao.mysql;

import bitcamp.myapp.dao.BoardDao;
import bitcamp.myapp.dao.DaoException;
import bitcamp.myapp.vo.Board;
import bitcamp.myapp.vo.Member;
import bitcamp.util.Component;
import bitcamp.util.DBConnectionPool;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Component
public class BoardDaoImpl implements BoardDao {

  DBConnectionPool dbConnectionPool;

  public BoardDaoImpl(DBConnectionPool dbConnectionPool) {
    this.dbConnectionPool = dbConnectionPool;
  }

  @Override
  public void add(Board board) {
    try (Connection connection = dbConnectionPool.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(
            "insert into boards(title, content, writer, category) "
                + "values(?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS
        )) {
      preparedStatement.setString(1, board.getTitle());
      preparedStatement.setString(2, board.getContent());
      preparedStatement.setInt(3, board.getWriter().getNo());
      preparedStatement.setInt(4, board.getCategory());
//      Statement statement = connection.createStatement();
//      statement.executeUpdate(String.format(
//          "insert into "
//              + "boards(title, content, writer, category) "
//              + "values('%s', '%s', '%s', '%d')",
//          board.getTitle(), board.getContent(), board.getWriter(), this.category));
      preparedStatement.executeUpdate();

      try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
        resultSet.next();
        board.setNo(resultSet.getInt(1));
      }

    } catch (Exception e) {
      throw new DaoException("Data Loading Error", e);
    }

  }

  @Override
  public int delete(int key) {
    try (Connection connection = dbConnectionPool.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(
            "delete from boards where board_no = ?"
        )) {
      preparedStatement.setInt(1, key);

      return preparedStatement.executeUpdate();

    } catch (Exception e) {
      throw new DaoException("Data Loading Error", e);
    }
  }

  @Override
  public List<Board> findAll(int category) {

    List<Board> boards = new ArrayList<>();

    try (Connection con = dbConnectionPool.getConnection();
        PreparedStatement pstmt = con.prepareStatement(
            "select\n"
                + "  b.board_no,\n"
                + "  b.title,\n"
                + "  b.created_date,\n"
                + "  count(file_no) file_count,\n"
                + "  m.member_no,\n"
                + "  m.name\n"
                + "from\n"
                + "  boards b left outer join board_files bf on b.board_no = bf.board_no\n"
                + "  inner join members m on b.writer = m.member_no\n"
                + "where\n"
                + "  b.category=?\n"
                + "group by\n"
                + "  board_no\n"
                + "order by\n"
                + "  board_no desc")) {

      pstmt.setInt(1, category);

      try (ResultSet rs = pstmt.executeQuery()) {

        while (rs.next()) {
          Board board = new Board();
          board.setNo(rs.getInt("board_no"));
          board.setTitle(rs.getString("title"));
          board.setCreatedDate(rs.getDate("created_date"));
          board.setFileCount(rs.getInt("file_count"));

          Member writer = new Member();
          writer.setNo(rs.getInt("member_no"));
          writer.setName(rs.getString("name"));

          board.setWriter(writer);

          boards.add(board);
        }
        return boards;
      }

    } catch (Exception e) {
      throw new DaoException("Data Loading Error", e);
    }
  }

  @Override
  public Board findBy(int key) {
    try (Connection connection = dbConnectionPool.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(
            "select"
                + "  b.board_no,\n"
                + "  b.title,\n"
                + "  b.content,"
                + "  b.created_date,\n"
                + "  m.member_no,\n"
                + "  m.name\n"
                + " from "
                + "  boards b inner join members m on b.writer=m.member_no\n"
                + " where board_no=?"
        )) {
      preparedStatement.setInt(1, key);

      ResultSet resultSet = preparedStatement.executeQuery();
      if (resultSet.next()) {
        Board board = new Board();
        board.setNo(resultSet.getInt("board_no"));
        board.setTitle(resultSet.getString("title"));
        board.setContent(resultSet.getString("content"));
        board.setCreatedDate(resultSet.getDate("created_date"));

        Member writer = new Member();
        writer.setNo(resultSet.getInt("member_no"));
        writer.setName(resultSet.getString("name"));

        board.setWriter(writer);

        return board;
      }
    } catch (Exception e) {
      throw new DaoException("Data Loading Error", e);
    }
    return null;
  }

  @Override
  public int update(Board board) {
    try (Connection connection = dbConnectionPool.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(
            "update boards set title = ?, content = ? "
                + "where board_no = ?"
        )) {
      preparedStatement.setString(1, board.getTitle());
      preparedStatement.setString(2, board.getContent());
      preparedStatement.setInt(3, board.getNo());

      return preparedStatement.executeUpdate();

    } catch (Exception e) {
      throw new DaoException("Data Loading Error", e);
    }
  }
}
