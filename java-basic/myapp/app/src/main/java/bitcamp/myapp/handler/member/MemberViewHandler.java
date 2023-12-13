package bitcamp.myapp.handler.member;

import bitcamp.menu.Menu;
import bitcamp.menu.MenuHandler;
import bitcamp.myapp.vo.Member;
import bitcamp.util.Prompt;
import java.util.ArrayList;

public class MemberViewHandler implements MenuHandler {

  Prompt prompt;
  ArrayList<Member> objectRepository;

  public MemberViewHandler(ArrayList<Member> objectRepository, Prompt prompt) {
    this.objectRepository = objectRepository;
    this.prompt = prompt;
  }

  public void action(Menu menu) throws Exception {
    System.out.printf("[%s]", menu.getTitle());

    int index = this.prompt.inputInt("몇 번을 조회?(0 ~)");

    Member member = objectRepository.get(index);

    if (member == null) {
      System.out.println("유효하지 않은 입력입니다.");
      return;
    }

    System.out.printf("이메일: %s\n", member.email);
    System.out.printf("이름: %s\n", member.name);
    System.out.printf("암호: %s\n", member.password);
    System.out.printf("가입일: %s\n", member.joinDate);

  }
}
