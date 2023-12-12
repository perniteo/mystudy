package bitcamp.myapp.handler.member;

import bitcamp.menu.Menu;
import bitcamp.menu.MenuHandler;
import bitcamp.myapp.vo.Member;
import bitcamp.util.Prompt;

public class MemberViewHandler implements MenuHandler {

  Prompt prompt;
  MemberRepository memberRepository = new MemberRepository();

  public MemberViewHandler(MemberRepository memberRepository, Prompt prompt) {
    this.memberRepository = memberRepository;
    this.prompt = prompt;
  }

  public void action(Menu menu) throws Exception {
    System.out.println("게시글 조회");
//    System.out.printf("%s 조회\n", this.title);
    int index = this.prompt.inputInt("몇 번을 조회?(0 ~)");
    if (index < 0 || index >= this.memberRepository.length) {
      System.out.println("유효하지 않은 입력입니다.");
      return;
    }
    Member member = this.memberRepository.arr[index];
    System.out.printf("이메일: %s\n", member.email);
    System.out.printf("이름: %s\n", member.name);
    System.out.printf("암호: %s\n", member.password);
    System.out.printf("가입일: %s\n", member.joinDate);

  }
}
