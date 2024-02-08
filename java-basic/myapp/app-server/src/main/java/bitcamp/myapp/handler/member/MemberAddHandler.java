package bitcamp.myapp.handler.member;

import bitcamp.menu.AbstractMenuHandler;
import bitcamp.myapp.dao.MemberDao;
import bitcamp.myapp.vo.Member;
import bitcamp.util.Prompt;
import java.util.Date;

public class MemberAddHandler extends AbstractMenuHandler {

  private final MemberDao memberDao;

  public MemberAddHandler(MemberDao memberDao) {
    this.memberDao = memberDao;
  }

  public MemberAddHandler(MemberDao memberDao, Prompt prompt) {
    super(prompt);
    this.memberDao = memberDao;
  }

  @Override
  protected void action(Prompt prompt) throws Exception {
    Member member = new Member();

    member.setEmail(prompt.input("이메일: "));
    member.setName(prompt.input("이름: "));
    member.setPassword(prompt.input("암호: "));
    member.setCreatedDate(new Date());

    this.memberDao.add(member);
  }

}
