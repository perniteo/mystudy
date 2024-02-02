package myDelivery.menu;

import myDelivery.util.Prompt;

public interface Menu {

  public abstract void execute(Prompt prompt) throws Exception;

  public abstract String getTitle();
}
