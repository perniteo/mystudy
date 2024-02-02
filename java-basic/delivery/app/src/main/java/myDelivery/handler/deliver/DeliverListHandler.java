package myDelivery.handler.deliver;


import myDelivery.dao.DeliverDao;
import myDelivery.menu.AbstractMenuHandler;
import myDelivery.util.Prompt;
import myDelivery.vo.Deliver;

public class DeliverListHandler extends AbstractMenuHandler {

  private final DeliverDao deliverDao;

  public DeliverListHandler(DeliverDao deliverDao, Prompt prompt) {
    super(prompt);
    this.deliverDao = deliverDao;
  }

  @Override
  protected void action() {
    System.out.println("내 택배 목록");
    System.out.printf("%s\t%-10s %-10s %-15s\n", "번호", "물품", "택배사", "운송장 번호");

    for (Deliver deliver : this.deliverDao.findAll()) {
      System.out.printf("%s\t\t%-10s %-10s %-15s\n", deliver.getNo(), deliver.getTitle(),
          deliver.getCarrierName(),
          deliver.getTrackId());
    }
  }
}

