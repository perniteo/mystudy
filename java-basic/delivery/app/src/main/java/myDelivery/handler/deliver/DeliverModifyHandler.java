package myDelivery.handler.deliver;


import myDelivery.DeliveryApp;
import myDelivery.dao.DeliverDao;
import myDelivery.menu.AbstractMenuHandler;
import myDelivery.util.Prompt;
import myDelivery.vo.Deliver;

public class DeliverModifyHandler extends AbstractMenuHandler {

  private final DeliverDao deliverDao;

  public DeliverModifyHandler(DeliverDao deliverDao, Prompt prompt) {
    super(prompt);
    this.deliverDao = deliverDao;
  }

  @Override
  protected void action() throws Exception {

    int key = prompt.inputInt("몇 번을 수정?(1~ ) ");

    Deliver oldDeliver = this.deliverDao.findBy(key);

    if (oldDeliver == null) {
      System.out.println("Wrong input key");
      return;
    }

    Deliver deliver = new Deliver();
    deliver.setNo(oldDeliver.getNo());
    deliver.setTitle(this.prompt.input("물품(%s): ", oldDeliver.getTitle()));
    deliver.setCarrierName(this.prompt.input("배송사(%s): ", oldDeliver.getCarrierName()));
    deliver.setTrackId(this.prompt.input("운송장 번호(%s): ", oldDeliver.getTrackId()));
    deliver.setDetailInfos(
        DeliveryApp.callLogAPI(deliver.getCarrierName(), deliver.getTrackId()));

    if (this.deliverDao.update(deliver) == 0) {
      System.out.println("Wrong input");
    } else {
      System.out.println("Update success");
    }

  }
}
