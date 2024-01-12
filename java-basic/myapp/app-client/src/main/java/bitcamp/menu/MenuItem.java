package bitcamp.menu;

import bitcamp.util.Prompt;
import java.util.Stack;


public class MenuItem extends AbstractMenu {

  //  String title;
  MenuHandler menuHandler;

  public MenuItem(String title, Stack<String> breadcrumb) {
    super(title, breadcrumb);
  }

  public MenuItem(String title, Stack<String> breadcrumb, MenuHandler menuHandler) {
    super(title, breadcrumb);
    this.menuHandler = menuHandler;
  }

//  @Override
//  public String getTitle() {
//    return this.title;
//  }

  @Override
  public void execute(Prompt prompt) throws Exception {
    if (this.menuHandler != null) {
      this.menuHandler.action(this);
    }
//    System.out.printf("[%s]\n", this.title);
  }
}
