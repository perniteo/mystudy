package bitcamp.myapp;

import bitcamp.menu.MenuGroup;
import bitcamp.myapp.dao.AssignmentDao;
import bitcamp.myapp.dao.BoardDao;
import bitcamp.myapp.dao.MemberDao;
import bitcamp.myapp.dao.json.AssignmentImpl;
import bitcamp.myapp.dao.json.BoardDaoImpl;
import bitcamp.myapp.dao.json.MemberDaoImpl;
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
import bitcamp.util.Prompt;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class App {

  Prompt prompt = new Prompt(System.in);

  BoardDao boardDao;
  AssignmentDao assignmentDao;
  MemberDao memberDao;
  BoardDao greetingDao;
  MenuGroup mainMenu;

  App() throws Exception {
    boardDao = new BoardDaoImpl("board.json");
    greetingDao = new BoardDaoImpl("greeting.json");
    assignmentDao = new AssignmentImpl("assignment.json");
    memberDao = new MemberDaoImpl("member.json");
//    boardRepository = loadData("board.json", Board.class);
//    greetingRepository = loadData("greeting.json", Board.class);
    prepareMenu();
  }

  public static void main(String[] args) throws Exception {
    System.out.println("Current working directory: " + System.getProperty("user.dir"));
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
    assignmentMenu.addItem("등록", new AssignAddHandler(assignmentDao, prompt));
    assignmentMenu.addItem("조회", new AssignViewHandler(assignmentDao, prompt));
    assignmentMenu.addItem("변경", new AssignModifyHandler(assignmentDao, prompt));
    assignmentMenu.addItem("삭제", new AssignDeleteHandler(assignmentDao, prompt));
    assignmentMenu.addItem("목록", new AssignListHandler(assignmentDao, prompt));

    MenuGroup boardMenu = mainMenu.addGroup("게시글");
    boardMenu.addItem("등록", new BoardAddHandler(boardDao, prompt));
    boardMenu.addItem("조회", new BoardViewHandler(boardDao, prompt));
    boardMenu.addItem("변경", new BoardModifyHandler(boardDao, prompt));
    boardMenu.addItem("삭제", new BoardDeleteHandler(boardDao, prompt));
    boardMenu.addItem("목록", new BoardListHandler(boardDao, prompt));

    MenuGroup memberMenu = mainMenu.addGroup("회원");
    memberMenu.addItem("등록", new MemberAddHandler(memberDao, prompt));
    memberMenu.addItem("조회", new MemberViewHandler(memberDao, prompt));
    memberMenu.addItem("변경", new MemberModifyHandler(memberDao, prompt));
    memberMenu.addItem("삭제", new MemberDeleteHandler(memberDao, prompt));
    memberMenu.addItem("목록", new MemberListHandler(memberDao, prompt));

    MenuGroup greetingMenu = mainMenu.addGroup("가입인사");
    greetingMenu.addItem("등록", new BoardAddHandler(greetingDao, prompt));
    greetingMenu.addItem("조회", new BoardViewHandler(greetingDao, prompt));
    greetingMenu.addItem("변경", new BoardModifyHandler(greetingDao, prompt));
    greetingMenu.addItem("삭제", new BoardDeleteHandler(greetingDao, prompt));
    greetingMenu.addItem("목록", new BoardListHandler(greetingDao, prompt));

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
//    saveData("assignment.json", assignmentRepository);
//    saveData("board.json", boardRepository);
//    saveData("member.json", memberRepository);
//    saveData("greeting.json", greetingRepository);
  }

  @SuppressWarnings("unused")
  <E> List<E> loadData(String filepath, Class<E> clazz) throws Exception {
    long start = 0;

    try (BufferedReader in = new BufferedReader((new FileReader(filepath)))) {
//      List<E> list = (List<E>) in.readObject();
//      dataList.addAll((List<E>) in.readObject());
      start = System.currentTimeMillis();

      StringBuilder sb = new StringBuilder();
      String str;

      while ((str = in.readLine()) != null) {
        sb.append(str);
      }

      return new GsonBuilder().setDateFormat("yyyy-MM-dd").create()
          .fromJson(sb.toString(), TypeToken.getParameterized(ArrayList.class, clazz).getType());

//      byte[] bytes = new byte[60000];
//      int size = in.read() << 8 | in.read();
//      int size = in.readInt();
//
//      for (int i = 0; i < size; i++) {
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
//        Assignment assignment = new Assignment();
//        assignment.setTitle(in.readUTF());
//        assignment.setContent(in.readUTF());
//        assignment.setDeadline(Date.valueOf((in.readUTF())));
//        assignmentRepository.add(assignment);
//      }
    } catch (Exception e) {
      System.out.println("Error for loading file");
      e.printStackTrace();
    } finally {
      long end = System.currentTimeMillis();
      if (start != 0) {
        System.out.println(end - start);
      }
    }
    return new ArrayList<>();
  }

  @SuppressWarnings("unused")
  void saveData(String filepath, List<?> dataList) throws Exception {
    try (BufferedWriter out = new BufferedWriter(new FileWriter((filepath)))) {
      long start = System.currentTimeMillis();

      out.write(new GsonBuilder().setDateFormat("yyyy-MM-dd").create().toJson(dataList));

      System.out.println(System.currentTimeMillis() - start);
    } catch (Exception e) {
      System.out.println("Error for saving file");
      e.printStackTrace();
    }
  }

  //
//  void saveData(String filepath, List<? extends CsvString> dataList) throws Exception {
//    try (FileWriter out = new FileWriter(filepath)) {
//      long start = System.currentTimeMillis();
//      for (CsvString csvString : dataList) {
//        out.write(csvString.toCsvString() + "\n");
//      }
////      out.writeObject(dataList);
//      // 저장할 데이터 개수를 2바이트로 출력한다.
////      out.write(assignmentRepository.size() >> 8);
////      out.write(assignmentRepository.size());
////      out.writeInt(assignmentRepository.size());
//
////      for (int i = 0; i < 400000; i++) {
////      for (Assignment assignment : assignmentRepository) {
////        out.writeUTF(assignment.getTitle());
////        out.writeUTF(assignment.getContent());
////        out.writeUTF(assignment.getDeadline().toString());
////        String title = assignment.getTitle();
////        byte[] bytes = title.getBytes(StandardCharsets.UTF_8);
////        // 바이트의 개수를 2바이트로 출력한다.
////        out.write(bytes.length >> 8);
////        out.write(bytes.length);
////        // 문자열의 바이트 배열을 출력한다.
////        out.write(bytes);
////
////        String content = assignment.getContent();
////        bytes = content.getBytes(StandardCharsets.UTF_8);
////        out.write(bytes.length >> 8);
////        out.write(bytes.length);
////        out.write(bytes);
////
////        String deadline = assignment.getDeadline().toString();
////        bytes = deadline.getBytes(StandardCharsets.UTF_8);
////        out.write(bytes);
////      }
////      }
//      long end = System.currentTimeMillis();
//      System.out.println(end - start);
//
//    } catch (Exception e) {
//      System.out.println("Error for saving file");
//      e.printStackTrace();
//    }
//  }
  @SuppressWarnings("unused")
  void loadBoard() {
    try (ObjectInputStream in = new ObjectInputStream(
        new BufferedInputStream(new FileInputStream("board.ser")))) {
//      boardRepository = (List<Board>) in.readObject();
//      byte[] bytes = new byte[60000];
//      int size = in.read() << 8 | in.read();
//      int size = in.readShort();

//      for (int i = 0; i < size; i++) {
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
//        Board board = new Board();
//        board.setTitle(in.readUTF());
//        board.setContent(in.readUTF());
//        board.setWriter(in.readUTF());
//        board.setCreatedDate(new java.util.Date(in.readLong()));
//
//        boardRepository.add(board);
//      }

    } catch (Exception e) {
      System.out.println("Error for loading file");
      e.printStackTrace();
    }
  }

  @SuppressWarnings("unused")
  void saveBoard() {
    try (ObjectOutputStream out = new ObjectOutputStream(
        new BufferedOutputStream(new FileOutputStream("board.ser")))) {
//      out.writeObject(boardRepository);
//      out.write(boardRepository.size() >> 8);
//      out.write(boardRepository.size());
//      out.writeShort(boardRepository.size());

//      for (Board board : boardRepository) {
//        byte[] bytes;
//        out.writeUTF(board.getTitle());
//        out.writeUTF(board.getContent());
//        out.writeUTF(board.getWriter());
//        out.writeLong(board.getCreatedDate().getTime());
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

//      }

    } catch (Exception e) {
      System.out.println("Error for saving file");
      e.printStackTrace();
    }
  }

  @SuppressWarnings("unused")
  void saveMember() {
    try (ObjectOutputStream out = new ObjectOutputStream(
        new BufferedOutputStream(new FileOutputStream("member.ser")))) {
      out.writeObject(memberDao);
//      out.write(boardRepository.size() >> 8);
//      out.write(boardRepository.size());
//      out.writeShort(boardRepository.size());

//      for (Member member : memberRepository) {
//        byte[] bytes;
//
//        String email = member.getEmail();
//        bytes = email.getBytes(StandardCharsets.UTF_8);
//        out.write(bytes.length >> 8);
//        out.write(bytes.length);
//        out.writeUTF(member.getEmail());
//        out.writeUTF(member.getName());
//        out.writeUTF(member.getPassword());
//        out.writeLong(member.getCreatedDate().getTime());
//      }
    } catch (Exception e) {
      System.out.println("Error for saving file");
      e.printStackTrace();
    }
  }

  @SuppressWarnings("unused")
  void loadMember() {
    try (ObjectInputStream in = new ObjectInputStream(
        new BufferedInputStream(new FileInputStream("member.ser")))) {
//      boardRepository = (List<Board>) in.readObject();
//      int size = in.readShort();

//      for (int i = 0; i < size; i++) {
//        Member member = new Member();
//
//        member.setEmail(in.readUTF());
//        member.setName(in.readUTF());
//        member.setPassword(in.readUTF());
//        member.setCreatedDate(new java.util.Date(in.readLong()));
//
//        memberRepository.add(member);
//      }

    } catch (Exception e) {
      System.out.println("Error for loading file");
      e.printStackTrace();
    }
  }

  @SuppressWarnings("unused")
  void saveGreeting() {
    try (ObjectOutputStream out = new ObjectOutputStream(
        new BufferedOutputStream(new FileOutputStream("greeting.ser")))) {

//      out.writeObject(greetingRepository);
//      out.writeShort(greetingRepository.size());

//      for (Board greeting : greetingRepository) {
//        out.writeUTF(greeting.getTitle());
//        out.writeUTF(greeting.getContent());
//        out.writeUTF(greeting.getWriter());
//        out.writeLong(greeting.getCreatedDate().getTime());
//      }

    } catch (Exception e) {
      System.out.println("Error for saving file");
      e.printStackTrace();
    }
  }

  @SuppressWarnings("unused")
  void loadGreeting() {
    try (ObjectInputStream in = new ObjectInputStream(
        new BufferedInputStream(new FileInputStream("greeting.ser")))) {

//      greetingRepository = (List<Board>) in.readObject();
//      int size = in.readShort();
//
//      for (int i = 0; i < size; i++) {
//        Board greeting = new Board();
//
//        greeting.setTitle(in.readUTF());
//        greeting.setContent(in.readUTF());
//        greeting.setWriter(in.readUTF());
//        greeting.setCreatedDate(new java.util.Date(in.readLong()));
//
//        greetingRepository.add(greeting);
//      }

    } catch (Exception e) {
      System.err.println("Error for loading file");
      e.printStackTrace();
    }
  }

}

