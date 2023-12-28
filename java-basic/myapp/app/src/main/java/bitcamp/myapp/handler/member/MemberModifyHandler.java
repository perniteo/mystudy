package bitcamp.myapp.handler.member;

import bitcamp.menu.AbstractMenuHandler;
import bitcamp.myapp.vo.Member;
import bitcamp.util.List;
import bitcamp.util.Prompt;

public class MemberModifyHandler extends AbstractMenuHandler {

  private final List<Member> objectRepository;
//  Prompt prompt;

  public MemberModifyHandler(List<Member> objectRepository, Prompt prompt) {
    super(prompt);
    this.objectRepository = objectRepository;
  }

  @Override
  protected void action() throws Exception {
//    System.out.printf("[%s]", menu.getTitle());

    int index = this.prompt.inputInt("몇 번을 변경?(0 ~");

    Member oldMember = this.objectRepository.get(index);

//    if (oldMember == null) {
//      System.out.println("유효하지 않은 입력입니다.");
//      return;
//    }

    Member member = new Member();
    member.setEmail(this.prompt.input("이메일(%s) :", member.getEmail()));
    member.setName(this.prompt.input("이름(%s) :", member.getName()));
    member.setPassword(this.prompt.input("암호(%s) :", member.getPassword()));
    member.setCreatedDate(oldMember.getCreatedDate());

    objectRepository.set(index, member);
  }

}
