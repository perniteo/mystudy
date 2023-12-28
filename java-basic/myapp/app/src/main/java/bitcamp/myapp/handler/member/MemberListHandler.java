package bitcamp.myapp.handler.member;

import bitcamp.menu.Menu;
import bitcamp.menu.MenuHandler;
import bitcamp.myapp.vo.Member;
import bitcamp.util.List;

public class MemberListHandler implements MenuHandler {

  List<Member> objectRepository;

  public MemberListHandler(List<Member> objectRepository) {
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
