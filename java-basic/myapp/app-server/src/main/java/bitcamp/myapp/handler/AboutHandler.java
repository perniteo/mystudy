package bitcamp.myapp.handler;

import bitcamp.menu.AbstractMenuHandler;
import bitcamp.util.Prompt;

public class AboutHandler extends AbstractMenuHandler {

  @Override
  protected void action(Prompt prompt) {
    prompt.println("비트캠프 네이버 데브옵스 과정 5기");
  }
}
