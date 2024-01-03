package bitcamp.myapp.handler.member;

import bitcamp.menu.AbstractMenuHandler;
import bitcamp.myapp.vo.Member;
import bitcamp.util.Prompt;
import java.util.List;

public class MemberViewHandler extends AbstractMenuHandler {

  //  Prompt prompt;
  private final List<Member> objectRepository;

  public MemberViewHandler(List<Member> objectRepository, Prompt prompt) {
    super(prompt);
    this.objectRepository = objectRepository;
  }

  protected void action() throws Exception {
//    System.out.printf("[%s]", menu.getTitle());

    int index = this.prompt.inputInt("몇 번을 조회?(0 ~)");

    Member member = objectRepository.get(index);

//    if (member == null) {
//      System.out.println("유효하지 않은 입력입니다.");
//      return;
//    }

    System.out.printf("이메일: %s\n", member.getEmail());
    System.out.printf("이름: %s\n", member.getName());
    System.out.printf("암호: %s\n", member.getPassword());
    System.out.printf("가입일: %1$tY-%1$tm-%1$td %1$tH:%1$tM:%1$tS\n", member.getCreatedDate());

  }
}
