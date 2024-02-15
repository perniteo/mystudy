package bitcamp.myapp.handler.member;

import bitcamp.menu.AbstractMenuHandler;
import bitcamp.myapp.dao.MemberDao;
import bitcamp.util.Prompt;

public class MemberDeleteHandler extends AbstractMenuHandler {

  private final MemberDao memberDao;

  public MemberDeleteHandler(MemberDao memberDao) {
    this.memberDao = memberDao;
  }

  public MemberDeleteHandler(MemberDao memberDao, Prompt prompt) {
    super(prompt);
    this.memberDao = memberDao;
  }

  @Override
  protected void action(Prompt prompt) throws Exception {

    int key = prompt.inputInt("몇 번을 삭제?(1 ~)");

    if (this.memberDao.delete(key) == 0) {
      prompt.println("Wrong input");
    } else {
      prompt.println("Delete success");
    }

  }
}
