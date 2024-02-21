package bitcamp.myapp.listener;

import bitcamp.myapp.dao.AssignmentDao;
import bitcamp.myapp.dao.AttachedFileDao;
import bitcamp.myapp.dao.BoardDao;
import bitcamp.myapp.dao.MemberDao;
import bitcamp.myapp.dao.mysql.AssignmentDaoImpl;
import bitcamp.myapp.dao.mysql.AttachedFileDaoImpl;
import bitcamp.myapp.dao.mysql.BoardDaoImpl;
import bitcamp.myapp.dao.mysql.MemberDaoImpl;
import bitcamp.util.DBConnectionPool;
import bitcamp.util.TransactionManager;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ContextLoaderListener implements ServletContextListener {

  @Override
  public void contextInitialized(ServletContextEvent sce) {
    System.out.println("allocate");

    DBConnectionPool connectionPool = new DBConnectionPool(
        "jdbc:mysql://db-ld250-kr.vpc-pub-cdb.ntruss.com/studydb",
        "study", "bitcamp!@#123");

    AssignmentDao assignmentDao = new AssignmentDaoImpl(connectionPool);
    MemberDao memberDao = new MemberDaoImpl(connectionPool);
    BoardDao boardDao = new BoardDaoImpl(connectionPool);
    AttachedFileDao attachedFileDao = new AttachedFileDaoImpl(connectionPool);
    TransactionManager txManager = new TransactionManager(connectionPool);

    ServletContext webAppStorage = sce.getServletContext();
    webAppStorage.setAttribute("assignmentDao", assignmentDao);
    webAppStorage.setAttribute("memberDao", memberDao);
    webAppStorage.setAttribute("boardDao", boardDao);
    webAppStorage.setAttribute("attachedFileDao", attachedFileDao);
    webAppStorage.setAttribute("txManager", txManager);

  }

  @Override
  public void contextDestroyed(ServletContextEvent sce) {
    System.out.println("deallocate");
  }
}
