package bitcamp.myapp.listener;

import bitcamp.myapp.dao.mysql.AssignmentDaoImpl;
import bitcamp.myapp.dao.mysql.AttachedFileDaoImpl;
import bitcamp.myapp.dao.mysql.BoardDaoImpl;
import bitcamp.myapp.dao.mysql.MemberDaoImpl;
import bitcamp.util.DBConnectionPool;
import bitcamp.util.TransactionManager;
import java.util.HashMap;
import java.util.Map;
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

    Map<String, Object> beanMap = new HashMap<>();

    beanMap.put("connectionPool", connectionPool);

    beanMap.put("assignmentDao", new AssignmentDaoImpl(connectionPool));
    beanMap.put("memberDao", new MemberDaoImpl(connectionPool));
    beanMap.put("boardDao", new BoardDaoImpl(connectionPool));
    beanMap.put("attachedFileDao", new AttachedFileDaoImpl(connectionPool));
    beanMap.put("txManager", new TransactionManager(connectionPool));

    ServletContext webAppStorage = sce.getServletContext();

    webAppStorage.setAttribute("beanMap", beanMap);
  }

  @Override
  public void contextDestroyed(ServletContextEvent sce) {
    System.out.println("deallocate");
  }
}
