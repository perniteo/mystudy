package bitcamp.myapp;

import bitcamp.io.BufferedDataInputStream;
import bitcamp.io.BufferedDataOutputStream;
import bitcamp.menu.MenuGroup;
import bitcamp.myapp.handler.HelpHandler;
import bitcamp.myapp.handler.assignment.AssignAddHandler;
import bitcamp.myapp.handler.assignment.AssignDeleteHandler;
import bitcamp.myapp.handler.assignment.AssignListHandler;
import bitcamp.myapp.handler.assignment.AssignModifyHandler;
import bitcamp.myapp.handler.assignment.AssignViewHandler;
import bitcamp.myapp.handler.board.BoardAddHandler;
import bitcamp.myapp.handler.board.BoardDeleteHandler;
import bitcamp.myapp.handler.board.BoardListHandler;
import bitcamp.myapp.handler.board.BoardModifyHandler;
import bitcamp.myapp.handler.board.BoardViewHandler;
import bitcamp.myapp.handler.member.MemberAddHandler;
import bitcamp.myapp.handler.member.MemberDeleteHandler;
import bitcamp.myapp.handler.member.MemberListHandler;
import bitcamp.myapp.handler.member.MemberModifyHandler;
import bitcamp.myapp.handler.member.MemberViewHandler;
import bitcamp.myapp.vo.Assignment;
import bitcamp.myapp.vo.Board;
import bitcamp.myapp.vo.Member;
import bitcamp.util.Prompt;
import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

public class App {

  Prompt prompt = new Prompt(System.in);

  List<Board> boardRepository = new LinkedList<>();
  List<Assignment> assignmentRepository = new LinkedList<>();
  List<Member> memberRepository = new LinkedList<>();
  List<Board> greetingRepository = new LinkedList<>();
  MenuGroup mainMenu;

  App() throws Exception {
    prepareMenu();
    loadAssignment();
    loadBoard();
    loadMember();
    loadGreeting();
  }

  public static void main(String[] args) throws Exception {
    new App().run();
//    int[] intArray = new int[5];

//    // 배열의 각 요소에 값 할당
//    for (int i = 0; i < intArray.length; i++) {
//      intArray[i] = i * 10;
//    }
//
//    // 배열의 각 요소의 값과 주소를 출력
//    for (int i = 0; i < intArray.length; i++) {
//      System.out.println(
//          "Element " + i + ": Value=" + intArray[i] + ", Address=" + System.identityHashCode(
//              intArray[i]));
  }

  void prepareMenu() {
    mainMenu = MenuGroup.getInstance("메인");

    MenuGroup assignmentMenu = mainMenu.addGroup("과제");
    assignmentMenu.addItem("등록", new AssignAddHandler(assignmentRepository, prompt));
    assignmentMenu.addItem("조회", new AssignViewHandler(assignmentRepository, prompt));
    assignmentMenu.addItem("변경", new AssignModifyHandler(assignmentRepository, prompt));
    assignmentMenu.addItem("삭제", new AssignDeleteHandler(assignmentRepository, prompt));
    assignmentMenu.addItem("목록", new AssignListHandler(assignmentRepository, prompt));

    MenuGroup boardMenu = mainMenu.addGroup("게시글");
    boardMenu.addItem("등록", new BoardAddHandler(boardRepository, prompt));
    boardMenu.addItem("조회", new BoardViewHandler(boardRepository, prompt));
    boardMenu.addItem("변경", new BoardModifyHandler(boardRepository, prompt));
    boardMenu.addItem("삭제", new BoardDeleteHandler(boardRepository, prompt));
    boardMenu.addItem("목록", new BoardListHandler(boardRepository, prompt));

    MenuGroup memberMenu = mainMenu.addGroup("회원");
    memberMenu.addItem("등록", new MemberAddHandler(memberRepository, prompt));
    memberMenu.addItem("조회", new MemberViewHandler(memberRepository, prompt));
    memberMenu.addItem("변경", new MemberModifyHandler(memberRepository, prompt));
    memberMenu.addItem("삭제", new MemberDeleteHandler(memberRepository, prompt));
    memberMenu.addItem("목록", new MemberListHandler(memberRepository, prompt));

    MenuGroup greetingMenu = mainMenu.addGroup("가입인사");
    greetingMenu.addItem("등록", new BoardAddHandler(greetingRepository, prompt));
    greetingMenu.addItem("조회", new BoardViewHandler(greetingRepository, prompt));
    greetingMenu.addItem("변경", new BoardModifyHandler(greetingRepository, prompt));
    greetingMenu.addItem("삭제", new BoardDeleteHandler(greetingRepository, prompt));
    greetingMenu.addItem("목록", new BoardListHandler(greetingRepository, prompt));

//    MenuGroup helpMenu = mainMenu.addGroup("도움말");
    mainMenu.addItem("도움말", new HelpHandler(prompt));
  }


  void run() throws Exception {
    while (true) {
      try {
        mainMenu.execute(prompt);
        prompt.close();
        break;
      } catch (Exception e) {
        System.err.println("Exception !");
      }
    }
    saveAssignment();
    saveBoard();
    saveMember();
    saveGreeting();
  }

  void loadAssignment() throws Exception {
    try (BufferedDataInputStream in = new BufferedDataInputStream("assignment.data")) {
//      byte[] bytes = new byte[60000];
//      int size = in.read() << 8 | in.read();
      int size = in.readInt();
      long start = System.currentTimeMillis();

      for (int i = 0; i < size; i++) {
//        int len = in.read() << 8 | in.read();
//        in.read(bytes, 0, len);
//        String title = new String(bytes, 0, len, StandardCharsets.UTF_8);
//
//        len = in.read() << 8 | in.read();
//        in.read(bytes, 0, len);
//        String content = new String(bytes, 0, len, StandardCharsets.UTF_8);
//
//        in.read(bytes, 0, 10);
//        Date deadline = Date.valueOf(new String(bytes, 0, 10, StandardCharsets.UTF_8));
        Assignment assignment = new Assignment();
        assignment.setTitle(in.readUTF());
        assignment.setContent(in.readUTF());
        assignment.setDeadline(Date.valueOf((in.readUTF())));
        assignmentRepository.add(assignment);
      }
      long end = System.currentTimeMillis();
      System.out.println(end - start);
    } catch (Exception e) {
      System.out.println("Error for loading file");
      e.printStackTrace();
    }
  }

  void saveAssignment() throws Exception {
    try (BufferedDataOutputStream out = new BufferedDataOutputStream("assignment.data")) {
      long start = System.currentTimeMillis();
      // 저장할 데이터 개수를 2바이트로 출력한다.
//      out.write(assignmentRepository.size() >> 8);
//      out.write(assignmentRepository.size());
      out.writeInt(assignmentRepository.size());

//      for (int i = 0; i < 400000; i++) {
      for (Assignment assignment : assignmentRepository) {
        out.writeUTF(assignment.getTitle());
        out.writeUTF(assignment.getContent());
        out.writeUTF(assignment.getDeadline().toString());
//        String title = assignment.getTitle();
//        byte[] bytes = title.getBytes(StandardCharsets.UTF_8);
//        // 바이트의 개수를 2바이트로 출력한다.
//        out.write(bytes.length >> 8);
//        out.write(bytes.length);
//        // 문자열의 바이트 배열을 출력한다.
//        out.write(bytes);
//
//        String content = assignment.getContent();
//        bytes = content.getBytes(StandardCharsets.UTF_8);
//        out.write(bytes.length >> 8);
//        out.write(bytes.length);
//        out.write(bytes);
//
//        String deadline = assignment.getDeadline().toString();
//        bytes = deadline.getBytes(StandardCharsets.UTF_8);
//        out.write(bytes);
      }
//      }
      long end = System.currentTimeMillis();
      System.out.println(end - start);

    } catch (Exception e) {
      System.out.println("Error for saving file");
      e.printStackTrace();
    }
  }

  void loadBoard() {
    try (BufferedDataInputStream in = new BufferedDataInputStream("board.data")) {
//      byte[] bytes = new byte[60000];
//      int size = in.read() << 8 | in.read();
      int size = in.readShort();

      for (int i = 0; i < size; i++) {
//        int len = in.read() << 8 | in.read();
//        in.read(bytes, 0, len);
//        String title = new String(bytes, 0, len, StandardCharsets.UTF_8);
//
//        len = in.read() << 8 | in.read();
//        in.read(bytes, 0, len);
//        String content = new String(bytes, 0, len, StandardCharsets.UTF_8);
//
//        len = in.read() << 8 | in.read();
//        in.read(bytes, 0, len);
//        String writer = new String(bytes, 0, len, StandardCharsets.UTF_8);
//
//        long date = ((long) in.read()) << 56 |
//            ((long) in.read()) << 48 |
//            ((long) in.read()) << 40 |
//            ((long) in.read()) << 32 |
//            ((long) in.read()) << 24 |
//            ((long) in.read()) << 16 |
//            ((long) in.read()) << 8 |
//            in.read();
        Board board = new Board();
        board.setTitle(in.readUTF());
        board.setContent(in.readUTF());
        board.setWriter(in.readUTF());
        board.setCreatedDate(new java.util.Date(in.readLong()));

        boardRepository.add(board);
      }


    } catch (Exception e) {
      System.out.println("Error for loading file");
      e.printStackTrace();
    }
  }

  void saveBoard() {
    try (BufferedDataOutputStream out = new BufferedDataOutputStream("board.data")) {

//      out.write(boardRepository.size() >> 8);
//      out.write(boardRepository.size());
      out.writeShort(boardRepository.size());

      for (Board board : boardRepository) {
//        byte[] bytes;

        out.writeUTF(board.getTitle());
        out.writeUTF(board.getContent());
        out.writeUTF(board.getWriter());
        out.writeLong(board.getCreatedDate().getTime());
//        String title = board.getTitle();
//        bytes = title.getBytes(StandardCharsets.UTF_8);
//        out.write(bytes.length >> 8);
//        out.write(bytes.length);
//        out.write(bytes);
//
//        String content = board.getTitle();
//        bytes = content.getBytes(StandardCharsets.UTF_8);
//        out.write(bytes.length >> 8);
//        out.write(bytes.length);
//        out.write(bytes);
//
//        String writer = board.getWriter();
//        bytes = writer.getBytes(StandardCharsets.UTF_8);
//        out.write(bytes.length >> 8);
//        out.write(bytes.length);
//        out.write(bytes);
//
////        String createDate = board.getCreatedDate().toString();
////        bytes = createDate.getBytes(StandardCharsets.UTF_8);
////        out.write(bytes);
////        long date = board.getCreatedDate().getTime();
////        out.write((int) (date >> 56));
////        out.write((int) (date >> 48));
////        out.write((int) (date >> 40));
////        out.write((int) (date >> 32));
////        out.write((int) (date >> 24));
////        out.write((int) (date >> 16));
////        out.write((int) (date >> 8));
////        out.write((int) date);
//        long date = board.getCreatedDate().getTime();
//        ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
//        buffer.putLong(date);
//        byte[] dateBytes = buffer.array();
//// 파일에 바이트 배열 쓰기
//        out.write(dateBytes);

      }

    } catch (Exception e) {
      System.out.println("Error for saving file");
      e.printStackTrace();
    }
  }

  void saveMember() {
    try (BufferedDataOutputStream out = new BufferedDataOutputStream("member.data")) {
//      out.write(boardRepository.size() >> 8);
//      out.write(boardRepository.size());
      out.writeShort(boardRepository.size());

      for (Member member : memberRepository) {
//        byte[] bytes;
//
//        String email = member.getEmail();
//        bytes = email.getBytes(StandardCharsets.UTF_8);
//        out.write(bytes.length >> 8);
//        out.write(bytes.length);
        out.writeUTF(member.getEmail());
        out.writeUTF(member.getName());
        out.writeUTF(member.getPassword());
        out.writeLong(member.getCreatedDate().getTime());
      }
    } catch (Exception e) {
      System.out.println("Error for saving file");
      e.printStackTrace();
    }
  }

  void loadMember() {
    try (BufferedDataInputStream in = new BufferedDataInputStream("member.data")) {

      int size = in.readShort();

      for (int i = 0; i < size; i++) {
        Member member = new Member();

        member.setEmail(in.readUTF());
        member.setName(in.readUTF());
        member.setPassword(in.readUTF());
        member.setCreatedDate(new java.util.Date(in.readLong()));

        memberRepository.add(member);
      }

    } catch (Exception e) {
      System.out.println("Error for loading file");
      e.printStackTrace();
    }
  }

  void saveGreeting() {
    try (BufferedDataOutputStream out = new BufferedDataOutputStream("greeting.data")) {

      out.writeShort(greetingRepository.size());

      for (Board greeting : greetingRepository) {
        out.writeUTF(greeting.getTitle());
        out.writeUTF(greeting.getContent());
        out.writeUTF(greeting.getWriter());
        out.writeLong(greeting.getCreatedDate().getTime());
      }

    } catch (Exception e) {
      System.out.println("Error for saving file");
      e.printStackTrace();
    }
  }

  void loadGreeting() {
    try (BufferedDataInputStream in = new BufferedDataInputStream("greeting.data")) {

      int size = in.readShort();

      for (int i = 0; i < size; i++) {
        Board greeting = new Board();

        greeting.setTitle(in.readUTF());
        greeting.setContent(in.readUTF());
        greeting.setWriter(in.readUTF());
        greeting.setCreatedDate(new java.util.Date(in.readLong()));

        greetingRepository.add(greeting);
      }

    } catch (Exception e) {
      System.err.println("Error for loading file");
      e.printStackTrace();
    }
  }

}

