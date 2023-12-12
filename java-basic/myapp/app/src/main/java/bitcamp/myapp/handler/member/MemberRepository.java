package bitcamp.myapp.handler.member;

//게시글 데이터를 보관

import bitcamp.myapp.vo.Member;

public class MemberRepository {

  private Member[] arr = new Member[3];
  private int length = 0;

  public void add(Member member) {
    if (this.arr.length == this.length) {
      Member[] newArr = new Member[this.arr.length + (this.arr.length
          / 2)];
      System.arraycopy(this.arr, 0, newArr, 0, this.length);
      this.arr = newArr;
    }
    this.arr[this.length++] = member;
  }

  public Member remove(int index) {
    if (index < 0 || index > this.arr.length) {
      return null;
    }

    Member deleted = this.arr[index];

    for (int i = index; i < (this.arr.length - 1); i++) {
      this.arr[i] = this.arr[i + 1];
    }
    this.arr[--length] = null;

    return deleted;
  }

  public Member[] toArray() {
    Member[] newArr = new Member[this.length];
    System.arraycopy(this.arr, 0, newArr, 0, this.length);
    return newArr;
  }

  public Member get(int index) {
    if (index < 0 || index > this.arr.length) {
      return null;
    }
    return this.arr[index];
  }

  public Member set(int index, Member member) {

    if (index < 0 || index > this.arr.length) {
      return null;
    }
    Member old = this.arr[index];
    this.arr[index] = member;

    return old;
  }
}
