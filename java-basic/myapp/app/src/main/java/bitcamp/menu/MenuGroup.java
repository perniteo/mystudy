package bitcamp.menu;

import bitcamp.util.LinkedList;
import bitcamp.util.List;
import bitcamp.util.Prompt;

public class MenuGroup extends AbstractMenu {

  //  String title;
//  int menuSize;
  private final List<Menu> menus = new LinkedList<>();


  public MenuGroup(String title) {
    super(title);
  }

  @Override
  public void execute(Prompt prompt) throws Exception {
    printMenu();
    while (true) {
      String input = prompt.input("%s> ", this.getTitle());

      if (input.equals("menu")) {
        this.printMenu();
        continue;
      } else if (input.equals("0")) {
        break;
      }

      try {
        int menuNum = Integer.parseInt(input);
        if (menuNum < 1 || menuNum > menus.size()) {
          System.out.println("wrong input");
          continue;
        }

        this.menus.get(menuNum - 1).execute(prompt);

      } catch (Exception e) {
        System.out.println("wrong input");
      }

//      switch (input) {
//        case "menu":
//          this.printMenu();
//          break;
//        case "1":
//          assignmentMenu.execute();
//          break;
//        case "2":
//          boardMenu.execute();
//          break;
//        case "3":
//          memberMenu.execute();
//          break;
//        case "4":
//          greetingMenu.execute();
//          break;
//        case "5":
//          helpMenu.execute();
//          break;
//        case "0":
//          System.out.println("종료합니다.");
//          return;
//        default:
//          System.out.println("메뉴 번호가 옳지 않습니다.");
//      }
    }

  }

  private void printMenu() {

    System.out.printf("[%s]\n", this.getTitle());

    for (int i = 0; i < this.menus.size(); i++) {
      System.out.printf("%d. %s\n", (i + 1), menus.get(i).getTitle());
    }

    System.out.printf("0. %s\n", "이전");
  }

//  @Override
//  public String getTitle() {
//    return this.title;
//  }

  public void add(Menu menu) {
//    if (this.menus.length == this.menuSize) {
//      Menu[] newMenus = new Menu[this.menuSize + (this.menuSize / 2)];
//      System.arraycopy(this.menus, 0, newMenus, 0, this.menuSize);
//      this.menus = newMenus;
//    }
//    this.menus[this.menuSize++] = menu;
    this.menus.add(menu);
  }

//  public void view(Menu menu) {
//
//  }

  public void remove(Menu menu) {
//    int index = this.indexOf(menu);
//    if (index == -1) {
//      System.out.println("wrong idx");
//      return;
//    }
//    for (int i = index; i < this.menuSize; i++) {
//      this.menus[i] = this.menus[i + 1];
//    }
//    this.menus[--this.menuSize] = null;
    this.menus.remove(menu);
  }

//  private int indexOf(Menu menu) {
//    for (int i = 0; i < menus.size(); i++) {
//      if (menus.get(i) == menu) {
//        return i;
//      }
//    }
//    return -1;
//  }


}
