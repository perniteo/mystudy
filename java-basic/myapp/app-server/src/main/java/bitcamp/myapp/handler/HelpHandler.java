package bitcamp.myapp.handler;

import bitcamp.menu.AbstractMenuHandler;
import bitcamp.util.Prompt;

public class HelpHandler extends AbstractMenuHandler {

  @Override
  protected void action(Prompt prompt) {
    prompt.println("아무 도움을 줄 수 없음");
  }
}
