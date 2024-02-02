package myDelivery.handler.deliver;


import java.util.List;
import myDelivery.dao.DeliverDao;
import myDelivery.menu.AbstractMenuHandler;
import myDelivery.util.Prompt;
import myDelivery.vo.Deliver;
import myDelivery.vo.DetailInfo;

public class DeliverViewHandler extends AbstractMenuHandler {

  DeliverDao deliverDao;

  public DeliverViewHandler(DeliverDao deliverDao, Prompt prompt) {
    super(prompt);
    this.deliverDao = deliverDao;
  }

  @Override
  protected void action() throws Exception {

    int key = this.prompt.inputInt("몇 번을 조회?(1 ~)");

    Deliver deliver = this.deliverDao.findBy(key);

    System.out.printf("물품 : %s\n", deliver.getTitle());
    System.out.printf("택배사 : %s\n", deliver.getCarrierName());
    System.out.printf("운송장 번호 : %s\n", deliver.getTrackId());

    List<DetailInfo> list = deliver.getDetailInfos();

    for (DetailInfo d : list) {
      System.out.printf("상태: %s\n", d.getStatus());
      System.out.printf("현황: %s\n", d.getContent());
      System.out.printf("장소: %s\n", d.getLocation());
      System.out.printf("일시: %s\n", d.getTime());
      System.out.println("--------------------------------------");
    }
  }
}
