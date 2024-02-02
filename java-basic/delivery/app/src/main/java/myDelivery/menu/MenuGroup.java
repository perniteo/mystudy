package myDelivery.menu;


import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import myDelivery.util.Prompt;


public class MenuGroup extends AbstractMenu {

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

        this.menus.get(menuNum - 1).execute(prompt);

      } catch (Exception e) {
        e.printStackTrace();
        System.out.println("wrong input1111");
      }

    }
    breadcrumb.pop();

  }

  private void printMenu() {

    System.out.printf("[%s]\n", this.getTitle());

    for (int i = 0; i < this.menus.size(); i++) {
      System.out.printf("%d. %s\n", (i + 1), menus.get(i).getTitle());
    }

    System.out.printf("0. %s\n", "종료");
  }


  public void add(Menu menu) {
    this.menus.add(menu);
  }


  public void remove(Menu menu) {
    this.menus.remove(menu);
  }


}
