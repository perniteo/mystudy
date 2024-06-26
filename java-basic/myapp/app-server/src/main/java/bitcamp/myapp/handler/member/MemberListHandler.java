package bitcamp.myapp.handler.member;

import bitcamp.menu.AbstractMenuHandler;
import bitcamp.myapp.dao.MemberDao;
import bitcamp.myapp.vo.Member;
import bitcamp.util.Prompt;

public class MemberListHandler extends AbstractMenuHandler {

  private final MemberDao memberDao;

  public MemberListHandler(MemberDao memberDao) {
    this.memberDao = memberDao;
  }

  public MemberListHandler(MemberDao memberDao, Prompt prompt) {
    super(prompt);
    this.memberDao = memberDao;
  }

  @Override
  protected void action(Prompt prompt) {

    prompt.printf("%-4s\t%-18s%s\t\t\t%s\n", "Key", "이메일", "이름", "가입일");

    for (Member member : this.memberDao.findAll()) {
      prompt.printf("%-4s\t%-20s%s\t\t%s\n", member.getNo(),
          member.getEmail(),
          member.getName(),
          member.getCreatedDate());
    }
  }

}
