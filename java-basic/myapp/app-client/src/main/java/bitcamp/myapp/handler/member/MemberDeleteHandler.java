package bitcamp.myapp.handler.member;

import bitcamp.menu.AbstractMenuHandler;
import bitcamp.myapp.dao.MemberDao;
import bitcamp.util.Prompt;

public class MemberDeleteHandler extends AbstractMenuHandler {

  private final MemberDao memberDao;

  public MemberDeleteHandler(MemberDao memberDao, Prompt prompt) {
    super(prompt);
    this.memberDao = memberDao;
  }

  @Override
  protected void action() throws Exception {
//    System.out.printf("[%s]", menu.getTitle());

    int key = this.prompt.inputInt("몇 번을 삭제?(1 ~)");

    if (this.memberDao.delete(key) == 0) {
      System.out.println("Wrong input");
    } else {
      System.out.println("Delete success");
    }

  }
}
