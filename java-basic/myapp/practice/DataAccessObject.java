package practice;

public interface DataAccessObject {

  default void sayHello() {
    System.out.println("Hello");
  }

  public void select();

  public void insert();

  public void update();

  public void delete();
}
