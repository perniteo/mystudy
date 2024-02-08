package bitcamp.myapp.handler.member;

import bitcamp.menu.AbstractMenuHandler;
import bitcamp.myapp.dao.MemberDao;
import bitcamp.myapp.vo.Member;
import bitcamp.util.Prompt;

public class MemberModifyHandler extends AbstractMenuHandler {

  private final MemberDao memberDao;

  public MemberModifyHandler(MemberDao memberDao) {
    this.memberDao = memberDao;
  }

  public MemberModifyHandler(MemberDao memberDao, Prompt prompt) {
    super(prompt);
    this.memberDao = memberDao;
  }

  @Override
  protected void action(Prompt prompt) throws Exception {

    int index = prompt.inputInt("몇 번을 변경?(1 ~) ");

    Member oldMember = this.memberDao.findBy(index);

    if (oldMember == null) {
      prompt.println("Wrong input key");
      return;
    }

    Member member = new Member();
    member.setNo(oldMember.getNo());
    member.setEmail(prompt.input("이메일(%s) :", oldMember.getEmail()));
    member.setName(prompt.input("이름(%s) :", oldMember.getName()));
    member.setPassword(prompt.input("암호(%s) :", oldMember.getPassword()));
    member.setCreatedDate(oldMember.getCreatedDate());

    if (this.memberDao.update(member) == 0) {
      prompt.println("Wrong input");
    } else {
      prompt.println("Update success");
    }
  }

}
