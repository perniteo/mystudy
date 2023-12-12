package bitcamp.myapp.handler.member;

import bitcamp.menu.Menu;
import bitcamp.menu.MenuHandler;
import bitcamp.myapp.vo.Member;
import bitcamp.util.Prompt;

public class MemberListHandler implements MenuHandler {

  Prompt prompt;
  MemberRepository memberRepository;

  public MemberListHandler(MemberRepository memberRepository, Prompt prompt) {
    this.memberRepository = memberRepository;
    this.prompt = prompt;
  }

  @Override
  public void action(Menu menu) {
    System.out.printf("[%s]", menu.getTitle());

    System.out.printf("%-18s%s\t%s\n", "이메일", "이름", "가입일");
    for (Member member : memberRepository.toArray()) {
      System.out.printf("%-20s%s\t\t%s\n", member.email, member.name, member.joinDate);
    }
  }

}
