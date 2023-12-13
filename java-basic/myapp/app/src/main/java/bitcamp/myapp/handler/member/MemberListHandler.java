package bitcamp.myapp.handler.member;

import bitcamp.menu.Menu;
import bitcamp.menu.MenuHandler;
import bitcamp.myapp.vo.Member;
import bitcamp.util.ObjectRepository;

public class MemberListHandler implements MenuHandler {

  ObjectRepository objectRepository;

  public MemberListHandler(ObjectRepository objectRepository) {
    this.objectRepository = objectRepository;
  }

  @Override
  public void action(Menu menu) {
    System.out.printf("[%s]", menu.getTitle());

    System.out.printf("%-18s%s\t%s\n", "이메일", "이름", "가입일");
    for (Object object : objectRepository.toArray()) {
      Member member = (Member) object;
      System.out.printf("%-20s%s\t\t%s\n", member.email, member.name, member.joinDate);
    }
  }

}
