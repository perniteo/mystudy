package bitcamp.myapp.handler.member;

import bitcamp.menu.Menu;
import bitcamp.menu.MenuHandler;
import bitcamp.myapp.vo.Member;
import bitcamp.util.Prompt;

public class MemberModifyHandler implements MenuHandler {

  MemberRepository memberRepository;
  Prompt prompt;

  public MemberModifyHandler(MemberRepository memberRepository, Prompt prompt) {
    this.memberRepository = memberRepository;
    this.prompt = prompt;
  }

  @Override
  public void action(Menu menu) throws Exception {
    System.out.printf("[%s]", menu.getTitle());

    int index = this.prompt.inputInt("몇 번을 변경?(0 ~");

    Member oldMember = this.memberRepository.get(index);

    if (oldMember == null) {
      System.out.println("유효하지 않은 입력입니다.");
      return;
    }

    Member member = new Member();
    member.email = this.prompt.input("이메일(%s) :", member.email);
    member.name = this.prompt.input("이름(%s) :", member.name);
    member.password = this.prompt.input("암호(%s) :", member.password);
    member.joinDate = this.prompt.input("가입일(%s) :", member.joinDate);

    memberRepository.set(index, member);
  }

}
