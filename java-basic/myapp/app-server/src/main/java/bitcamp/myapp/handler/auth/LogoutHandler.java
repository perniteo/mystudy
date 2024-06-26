package bitcamp.myapp.handler.auth;

import bitcamp.menu.AbstractMenuHandler;
import bitcamp.util.Prompt;

public class LogoutHandler extends AbstractMenuHandler {

  @Override
  protected void action(Prompt prompt) {
    prompt.getSession().invalidate();
    prompt.println("Logout");
  }

}
