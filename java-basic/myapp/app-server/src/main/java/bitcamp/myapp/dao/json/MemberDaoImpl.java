package bitcamp.myapp.dao.json;

import bitcamp.myapp.dao.MemberDao;
import bitcamp.myapp.vo.Member;
import java.util.List;

public class MemberDaoImpl extends AbstractDao<Member> implements MemberDao {

  private int lastKey;

  public MemberDaoImpl(String filepath) {
    super(filepath);

    if (list.isEmpty()) {
      lastKey = 1;
    } else {
      lastKey = list.getLast().getNo();
    }
  }

  public void add(Member member) {
    member.setNo(++lastKey);
    this.list.add(member);
    saveData();
  }

  public int delete(int key) {
    int index = indexOf(key);
    if (index == -1) {
      System.out.println("Wrong input key");
      return 0;
    }
    this.list.remove(index);
    saveData();
    return 1;
  }

  public int update(Member member) {
    int index = indexOf(member.getNo());
    if (index == -1) {
      return 0;
    }
    this.list.set(index, member);
    saveData();
    return 1;
  }

  public List<Member> findAll() {
    return this.list.subList(0, list.size());
  }

  public Member findBy(int key) {
    int index = indexOf(key);
    if (index == -1) {
      System.out.println("Wrong input key");
      return null;
    }
    return this.list.get(index);
  }

  @Override
  public Member findByEmailAndPassword(String email, String password) {
    return null;
  }

  public int indexOf(int key) {
    for (int i = 0; i < list.size(); i++) {
      if (list.get(i).getNo() == key) {
        return i;
      }
    }
    return -1;
  }

}
