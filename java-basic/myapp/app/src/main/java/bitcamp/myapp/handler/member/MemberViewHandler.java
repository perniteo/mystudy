package bitcamp.myapp.handler.member;

import bitcamp.menu.AbstractMenuHandler;
import bitcamp.myapp.dao.MemberDao;
import bitcamp.myapp.vo.Member;
import bitcamp.util.Prompt;

public class MemberViewHandler extends AbstractMenuHandler {

  private final MemberDao memberDao;

  public MemberViewHandler(MemberDao memberDao) {
    this.memberDao = memberDao;
  }

  public MemberViewHandler(MemberDao memberDao, Prompt prompt) {
    super(prompt);
    this.memberDao = memberDao;
  }

  protected void action(Prompt prompt) throws Exception {

    int index = prompt.inputInt("몇 번을 조회?(1 ~)");

    Member member = memberDao.findBy(index);
    if (member == null) {
      prompt.println("Wrong input number");
      return;
    }

    prompt.printf("Key: %s\n", member.getNo());
    prompt.printf("이메일: %s\n", member.getEmail());
    prompt.printf("이름: %s\n", member.getName());
    prompt.printf("암호: %s\n", member.getPassword());
    prompt.printf("가입일: %1$tY-%1$tm-%1$td %1$tH:%1$tM:%1$tS\n", member.getCreatedDate());

  }
}
