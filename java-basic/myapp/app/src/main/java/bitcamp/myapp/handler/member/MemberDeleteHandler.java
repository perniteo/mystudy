package bitcamp.myapp.handler.member;

import bitcamp.menu.Menu;
import bitcamp.menu.MenuHandler;
import bitcamp.myapp.vo.Member;
import bitcamp.util.Prompt;
import java.util.ArrayList;

public class MemberDeleteHandler implements MenuHandler {

  ArrayList<Member> objectRepository;
  Prompt prompt;

  public MemberDeleteHandler(ArrayList<Member> objectRepository, Prompt prompt) {
    this.objectRepository = objectRepository;
    this.prompt = prompt;
  }

  @Override
  public void action(Menu menu) throws Exception {
    System.out.printf("[%s]", menu.getTitle());

    int index = this.prompt.inputInt("몇 번을 삭제?(0 ~)");
    if (objectRepository.remove(index) == null) {
      System.out.println("유효하지 않은 입력입니다.");
    }

  }
}
