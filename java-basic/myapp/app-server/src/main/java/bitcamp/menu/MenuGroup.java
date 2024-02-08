package bitcamp.menu;


import bitcamp.util.Prompt;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;


public class MenuGroup extends AbstractMenu {

  private final List<Menu> menus = new LinkedList<>();

  private MenuGroup(String title) {
    super(title);
  }

  public static MenuGroup getInstance(String title) {
    return new MenuGroup(title);
  }

  public MenuItem addItem(String title, MenuHandler handler) {
    MenuItem menuItem = new MenuItem(title, handler);
    this.add(menuItem);
    return menuItem;
  }

  public MenuGroup addGroup(String title) {
    MenuGroup menuGroup = new MenuGroup(title);
    this.add(menuGroup);
    return menuGroup;
  }

  @Override
  public void execute(Prompt prompt) throws Exception {

    prompt.pushPath(this.getTitle());

    this.printMenu(prompt);

    while (true) {
      String input = prompt.input("%s> ", prompt.getMenuPath());

      if (input.equals("menu")) {
        this.printMenu(prompt);
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

    }
    prompt.popPath();

  }

  private void printMenu(Prompt prompt) {
    prompt.printf("[%s]\n", this.getTitle());

    Iterator<Menu> iterator = this.menus.iterator();
    int i = 1;
    while (iterator.hasNext()) {
      Menu menu = iterator.next();
      prompt.printf("%d. %s\n", i++, menu.getTitle());
    }

    prompt.printf("0. %s\n", "이전");
  }


  public void add(Menu menu) {
    this.menus.add(menu);
  }

  public void remove(Menu menu) {
    this.menus.remove(menu);

  }
}
