package bitcamp.myapp.handler.member;

import bitcamp.menu.Menu;
import bitcamp.menu.MenuHandler;
import bitcamp.myapp.vo.Member;
import bitcamp.util.Prompt;

public class MemberAddHandler implements MenuHandler {

  Prompt prompt;
  MemberRepository memberRepository;

  public MemberAddHandler(MemberRepository boardRepository, Prompt prompt) {
    this.memberRepository = boardRepository;
    this.prompt = prompt;
  }

  @Override
  public void action(Menu menu) throws Exception {
    if (this.memberRepository.length == this.memberRepository.arr.length) {
      Member[] newArr = new Member[this.memberRepository.length + (this.memberRepository.length
          / 2)];
      System.arraycopy(this.memberRepository.arr, 0, newArr, 0, this.memberRepository.length);
      this.memberRepository.arr = newArr;
    }
//    System.out.printf("%s 등록:\n", this.title);

    Member member = new Member();
    member.email = this.prompt.input("이메일: ");
    member.name = this.prompt.input("이름: ");
    member.password = this.prompt.input("암호: ");
    member.joinDate = this.prompt.input("가입일: ");
    this.memberRepository.arr[this.memberRepository.length++] = member;
  }

}
