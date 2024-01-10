package bitcamp.myapp.dao;

import bitcamp.myapp.vo.Assignment;

public class DaoTest extends AbstractDao<Assignment> {

  public static void main(String[] args) {

    DaoTest daoTest = new DaoTest();
    daoTest.loadData("app/assignment.json");

    for (Assignment assignment : daoTest.list) {
      System.out.println(assignment);
    }
  }
}
