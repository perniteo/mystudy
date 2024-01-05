package bitcamp.myapp.handler.member;

import bitcamp.menu.AbstractMenuHandler;
import bitcamp.myapp.vo.Member;
import bitcamp.util.Prompt;
import java.util.List;

public class MemberListHandler extends AbstractMenuHandler {

  private final List<Member> objectRepository;

  public MemberListHandler(List<Member> objectRepository, Prompt prompt) {
    super(prompt);
    this.objectRepository = objectRepository;
  }

  @Override
  protected void action() {
//    System.out.printf("[%s]", menu.getTitle());
//    Member[] members = this.objectRepository.toArray(new Member[0]);
//      Member member = (Member) object;

    System.out.printf("%-18s%s\t%s\n", "이메일", "이름", "가입일");

    for (Member member : this.objectRepository) {
      System.out.printf("%-20s%s\t\t%s\n", member.getEmail(), member.getName(),
          member.getCreatedDate());
    }
  }

}
