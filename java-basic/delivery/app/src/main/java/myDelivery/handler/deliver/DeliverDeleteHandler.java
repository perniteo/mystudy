package myDelivery.handler.deliver;


import myDelivery.dao.DeliverDao;
import myDelivery.menu.AbstractMenuHandler;
import myDelivery.util.Prompt;

public class DeliverDeleteHandler extends AbstractMenuHandler {

  private final DeliverDao deliverDao;

  public DeliverDeleteHandler(DeliverDao deliverDao, Prompt prompt) {
    super(prompt);
    this.deliverDao = deliverDao;
  }

  protected void action() throws Exception {

    int key = prompt.inputInt("몇 번을 삭제? ");

    if (this.deliverDao.delete(key) == 0) {
      System.out.println("Wrong input");
    } else {
      System.out.println("Delete success");
    }
  }
}
