package bitcamp.myapp.handler.member;

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
  public void action() throws Exception {
    System.out.println("회원정보 삭제");
//    System.out.printf("%s 삭제\n", this.title);

    int index = this.prompt.inputInt("몇 번을 삭제?(0 ~)");
    if (index < 0 || index >= this.memberRepository.length) {
      System.out.println("유효하지 않은 입력입니다.");
      return;
    }
    for (int i = index; i < (this.memberRepository.length - 1); i++) {
      this.memberRepository.arr[i] = this.memberRepository.arr[i + 1];
    }
    this.memberRepository.arr[--memberRepository.length] = null;
  }

}
