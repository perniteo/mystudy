package myDelivery.menu;


import java.util.Stack;
import myDelivery.util.Prompt;


public class MenuItem extends AbstractMenu {

  MenuHandler menuHandler;

  public MenuItem(String title, Stack<String> breadcrumb) {
    super(title, breadcrumb);
  }

  public MenuItem(String title, Stack<String> breadcrumb, MenuHandler menuHandler) {
    super(title, breadcrumb);
    this.menuHandler = menuHandler;
  }


  @Override
  public void execute(Prompt prompt) throws Exception {
    if (this.menuHandler != null) {
      this.menuHandler.action(this);
    }
  }
}
