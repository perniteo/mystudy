package bitcamp.menu;

import bitcamp.util.Prompt;

public class MenuItem extends AbstractMenu {

  private MenuHandler menuHandler;

  public MenuItem(String title) {
    super(title);
  }

  public MenuItem(String title, MenuHandler menuHandler) {
    super(title);
    this.menuHandler = menuHandler;
  }


  @Override
  public void execute(Prompt prompt) throws Exception {
    if (this.menuHandler != null) {
      this.menuHandler.action(this, prompt);
    }
  }
}
