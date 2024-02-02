package myDelivery.dao;

import java.util.List;
import myDelivery.vo.Deliver;

public interface DeliverDao {

  void add(Deliver deliver);

  int delete(int key);

  List<Deliver> findAll();

  Deliver findBy(int key);

  int update(Deliver deliver);
}
