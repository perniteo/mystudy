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
    System.out.println("게시글 목록");
//    System.out.printf("%s 목록을 호출합니다.\n", this.title);
    System.out.printf("%-18s%s\t%s\n", "이메일", "이름", "가입일");
    for (int i = 0; i < this.memberRepository.length; i++) {
      Member member = this.memberRepository.arr[i];
      System.out.printf("%-20s%s\t\t%s\n", member.email, member.name, member.joinDate);
    }
  }

}
