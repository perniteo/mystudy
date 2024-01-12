package bitcamp.myapp.handler.member;

import bitcamp.menu.AbstractMenuHandler;
import bitcamp.myapp.dao.MemberDao;
import bitcamp.myapp.vo.Member;
import bitcamp.util.Prompt;

public class MemberModifyHandler extends AbstractMenuHandler {

  private final MemberDao memberDao;

  public MemberModifyHandler(MemberDao memberDao, Prompt prompt) {
    super(prompt);
    this.memberDao = memberDao;
  }

  @Override
  protected void action() throws Exception {

    int index = this.prompt.inputInt("몇 번을 변경?(0 ~");

    Member oldMember = this.memberDao.findBy(index);

    if (oldMember == null) {
      System.out.println("Wrong input key");
      return;
    }

    Member member = new Member();
    member.setNo(oldMember.getNo());
    member.setEmail(this.prompt.input("이메일(%s) :", member.getEmail()));
    member.setName(this.prompt.input("이름(%s) :", member.getName()));
    member.setPassword(this.prompt.input("암호(%s) :", member.getPassword()));
    member.setCreatedDate(oldMember.getCreatedDate());

    if (this.memberDao.update(member) == 0) {
      System.out.println("Wrong input");
    } else {
      System.out.println("Update success");
    }
  }

}
