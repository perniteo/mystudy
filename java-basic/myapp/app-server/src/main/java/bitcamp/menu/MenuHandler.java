package bitcamp.menu;

import bitcamp.util.Prompt;

public interface MenuHandler {

  default void action(Menu menu, Prompt prompt) throws Exception {

  }
}
