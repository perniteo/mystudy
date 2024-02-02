package myDelivery.dao.mysql;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import myDelivery.dao.DaoException;
import myDelivery.dao.DeliverDao;
import myDelivery.vo.Deliver;
import myDelivery.vo.DetailInfo;
import org.json.JSONArray;
import org.json.JSONObject;

public class DeliverDaoImpl implements DeliverDao {

  Connection connection;

  public DeliverDaoImpl(Connection connection) {
    this.connection = connection;
  }

  @Override
  public void add(Deliver deliver) {
    try (Statement statement = connection.createStatement()) {
      statement.executeUpdate(String.format("insert into "
              + "delivers(title, carrierName, trackId, detail) "
              + "values('%s', '%s', '%s', '%s')"
          , deliver.getTitle(), deliver.getCarrierName(), deliver.getTrackId(),
          new JSONArray(deliver.getDetailInfos())));
    } catch (Exception e) {
      throw new DaoException("Data Loading Error", e);
    }
  }


  @Override
  public int delete(int key) {
    try (Statement statement = connection.createStatement()) {
      return statement.executeUpdate("delete from delivers "
          + "where deliver_no = " + key);
    } catch (Exception e) {
      throw new DaoException("Data Loading Error", e);
    }
  }

  @Override
  public List<Deliver> findAll() {

    List<Deliver> delivers = new ArrayList<>();

    try (Statement statement = connection.createStatement()) {
      ResultSet resultSet = statement.executeQuery("select * "
          + "from delivers");
      while (resultSet.next()) {
        Deliver deliver = new Deliver();
        deliver.setNo(resultSet.getInt("deliver_no"));
        deliver.setTitle(resultSet.getString("title"));
        deliver.setCarrierName(resultSet.getString("carrierName"));
        deliver.setTrackId(resultSet.getString("trackId"));

        delivers.add(deliver);
      }

    } catch (Exception e) {
      throw new DaoException("Data Loading Error", e);
    }
    return delivers;
  }

  @Override
  public Deliver findBy(int key) {
    try (Statement statement = connection.createStatement()) {
      ResultSet resultSet = statement.executeQuery("select * "
          + "from delivers "
          + "where deliver_no = " + key);

      if (resultSet.next()) {
        Deliver deliver = new Deliver();

        List<DetailInfo> list = new ArrayList<>();

        deliver.setNo(resultSet.getInt("deliver_no"));
        deliver.setTitle(resultSet.getString("title"));
        deliver.setCarrierName(resultSet.getString("carrierName"));
        deliver.setTrackId(resultSet.getString("trackId"));
        JSONArray detail = new JSONArray(resultSet.getString("detail"));

        for (int i = 0; i < detail.length(); i++) {
          JSONObject jsonObject = detail.getJSONObject(i);

          DetailInfo detailInfo = new DetailInfo();
          detailInfo.setContent(jsonObject.getString("content"));
          detailInfo.setTime(jsonObject.getString("time"));
          detailInfo.setStatus(jsonObject.getString("status"));
          detailInfo.setLocation(jsonObject.getString("location"));

          list.add(detailInfo);
        }

        deliver.setDetailInfos(list);

        return deliver;
      }

    } catch (Exception e) {
      throw new DaoException("Data Loading Error", e);
    }
    return null;
  }

  @Override
  public int update(Deliver deliver) {

    try (Statement statement = connection.createStatement()) {
      return statement.executeUpdate(String.format("update delivers " +
              "set title = '%s', carrierName = '%s', trackId = '%s', detail = '%s' " +
              "where deliver_no = '%d'",
          deliver.getTitle(), deliver.getCarrierName(), deliver.getTrackId(),
          new JSONArray(deliver.getDetailInfos()),
          deliver.getNo()));
    } catch (Exception e) {
      throw new DaoException("Data Loading Error", e);
    }

  }
}
