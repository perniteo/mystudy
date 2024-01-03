package bitcamp.menu;


import bitcamp.util.Prompt;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;


public class MenuGroup extends AbstractMenu {

  //  String title;
//  int menuSize;
  private final List<Menu> menus = new LinkedList<>();

  private MenuGroup(String title, Stack<String> breadScrum) {
    super(title, breadScrum);
  }

  public static MenuGroup getInstance(String title) {
    return new MenuGroup(title, new Stack<>());
  }

  public MenuItem addItem(String title, MenuHandler handler) {
    MenuItem menuItem = new MenuItem(title, this.breadcrumb, handler);
    this.add(menuItem);
    return menuItem;
  }

  public MenuGroup addGroup(String title) {
    MenuGroup menuGroup = new MenuGroup(title, this.breadcrumb);
    this.add(menuGroup);
    return menuGroup;
  }

  @Override
  public void execute(Prompt prompt) throws Exception {

    breadcrumb.push(this.getTitle());

    this.printMenu();

    while (true) {
      String input = prompt.input("%s> ", this.getMenuPath());

      if (input.equals("menu")) {
        this.printMenu();
        continue;
      } else if (input.equals("0")) {
        break;
      }

      try {
        int menuNum = Integer.parseInt(input);
//        if (menuNum < 1 || menuNum > menus.size()) {
//          System.out.println("wrong input");
//          continue;
//        }

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
    breadcrumb.pop();

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
    this.menus.add(menu);
//    if (this.menus.length == this.menuSize) {
//      Menu[] newMenus = new Menu[this.menuSize + (this.menuSize / 2)];
//      System.arraycopy(this.menus, 0, newMenus, 0, this.menuSize);
//      this.menus = newMenus;
//    }
//    this.menus[this.menuSize++] = menu;
  }

//  public void view(Menu menu) {
//
//  }

  public void remove(Menu menu) {
    this.menus.remove(menu);
//    int index = this.indexOf(menu);
//    if (index == -1) {
//      System.out.println("wrong idx");
//      return;
//    }
//    for (int i = index; i < this.menuSize; i++) {
//      this.menus[i] = this.menus[i + 1];
//    }
//    this.menus[--this.menuSize] = null;
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
