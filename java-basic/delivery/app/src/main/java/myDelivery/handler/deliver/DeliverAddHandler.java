package myDelivery.handler.deliver;


import static myDelivery.DeliveryApp.callLogAPI;

import myDelivery.dao.DeliverDao;
import myDelivery.menu.AbstractMenuHandler;
import myDelivery.util.Prompt;
import myDelivery.vo.Deliver;

public class DeliverAddHandler extends AbstractMenuHandler {

  private final DeliverDao deliverDao;

  public DeliverAddHandler(DeliverDao deliverDao, Prompt prompt) {
    super(prompt);
    this.deliverDao = deliverDao;
  }

  @Override
  protected void action() throws Exception {

    try {
      Deliver deliver = new Deliver();
      deliver.setTitle(this.prompt.input("어떤 택배인가요? "));
      deliver.setCarrierName(this.prompt.input("택배사(도움말 참조) : "));
      deliver.setTrackId(this.prompt.input("운송장 번호 : "));
      deliver.setDetailInfos(callLogAPI(deliver.getCarrierName(), deliver.getTrackId()));
      System.out.println(deliver.getTitle());
      System.out.println(deliver.getTrackId());

      deliverDao.add(deliver);
    } catch (Exception e) {
      e.printStackTrace();
      System.err.println("Wrong input");
    }
  }

}
