package bitcamp.menu;

import bitcamp.util.Prompt;

public class MenuItem implements Menu {

  String title;
  MenuHandler menuHandler;

  public MenuItem(String title) {
    this.title = title;
  }

  public MenuItem(String title, MenuHandler menuHandler) {
    this(title);
    this.menuHandler = menuHandler;
  }

  @Override
  public String getTitle() {
    return this.title;
  }

  @Override
  public void execute(Prompt prompt) throws Exception {
    if (this.menuHandler != null) {
      this.menuHandler.action(this);
    }
//    System.out.printf("[%s]\n", this.title);
  }
}
