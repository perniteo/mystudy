package bitcamp.menu;

import bitcamp.util.Prompt;

public interface Menu {

  public abstract void execute(Prompt prompt) throws Exception;

//  void execute() throws Exception;

  public abstract String getTitle();
}
