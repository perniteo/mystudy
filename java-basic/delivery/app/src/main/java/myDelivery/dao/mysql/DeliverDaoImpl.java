package myDelivery.dao.mysql;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import myDelivery.DeliveryApp;
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
    try (PreparedStatement preparedStatement = connection.prepareStatement(
        "insert into delivers(title, carrierName, trackId, detail)"
            + "values(?, ?, ?, ?)"
    )) {
      preparedStatement.setString(1, deliver.getTitle());
      preparedStatement.setString(2, deliver.getCarrierName());
      preparedStatement.setString(3, deliver.getTrackId());
      preparedStatement.setString(4, new JSONArray(deliver.getDetailInfos()).toString());

      preparedStatement.executeUpdate();
//      statement.executeUpdate(String.format("insert into "
//              + "delivers(title, carrierName, trackId, detail) "
//              + "values('%s', '%s', '%s', '%s')"
//          , deliver.getTitle(), deliver.getCarrierName(), deliver.getTrackId(),
//          new JSONArray(deliver.getDetailInfos())));
    } catch (Exception e) {
      throw new DaoException("Data Loading Error", e);
    }
  }


  @Override
  public int delete(int key) {
    try (PreparedStatement preparedStatement = connection.prepareStatement(
        "delete from delivers where deliver_no = ?"
    )) {
      preparedStatement.setInt(1, key);
      return preparedStatement.executeUpdate();
    } catch (Exception e) {
      throw new DaoException("Data Loading Error", e);
    }
  }

  @Override
  public List<Deliver> findAll() {

    List<Deliver> delivers = new ArrayList<>();

    try (PreparedStatement preparedStatement = connection.prepareStatement(
        "select * from delivers order by deliver_no desc"
    )) {
      ResultSet resultSet = preparedStatement.executeQuery();

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
    try (PreparedStatement preparedStatement = connection.prepareStatement(
        "select * from delivers where deliver_no = ?"
    )) {
      preparedStatement.setInt(1, key);

      ResultSet resultSet = preparedStatement.executeQuery();

      if (resultSet.next()) {
        Deliver deliver = new Deliver();

        List<DetailInfo> list = new ArrayList<>();

        deliver.setNo(resultSet.getInt("deliver_no"));
        deliver.setTitle(resultSet.getString("title"));
        deliver.setCarrierName(resultSet.getString("carrierName"));
        deliver.setTrackId(resultSet.getString("trackId"));
        deliver.setDetailInfos(
            DeliveryApp.callLogAPI(deliver.getCarrierName(), deliver.getTrackId()));
        update(deliver);
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

    try (PreparedStatement preparedStatement = connection.prepareStatement(
        "update delivers set title = ?, carrierName = ?, trackId = ?, detail = ?"
            + "where deliver_no = ?"
    )) {
      preparedStatement.setString(1, deliver.getTitle());
      preparedStatement.setString(2, deliver.getCarrierName());
      preparedStatement.setString(3, deliver.getTrackId());
      preparedStatement.setString(4, new JSONArray(deliver.getDetailInfos()).toString());
      preparedStatement.setInt(5, deliver.getNo());

      return preparedStatement.executeUpdate();
      
    } catch (Exception e) {
      throw new DaoException("Data Loading Error", e);
    }

  }
}
