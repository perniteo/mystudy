package bitcamp.myapp.handler.member;

import bitcamp.menu.Menu;
import bitcamp.menu.MenuHandler;
import bitcamp.util.Prompt;

public class MemberDeleteHandler implements MenuHandler {

  MemberRepository memberRepository;
  Prompt prompt;

  public MemberDeleteHandler(MemberRepository memberRepository, Prompt prompt) {
    this.memberRepository = memberRepository;
    this.prompt = prompt;
  }

  @Override
  public void action(Menu menu) throws Exception {
    System.out.printf("[%s]", menu.getTitle());

    int index = this.prompt.inputInt("몇 번을 삭제?(0 ~)");
    if (memberRepository.remove(index) == null) {
      System.out.println("유효하지 않은 입력입니다.");
    }

  }
}
