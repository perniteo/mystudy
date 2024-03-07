package bitcamp.myapp.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@WebListener
public class ContextLoaderListener implements ServletContextListener {

  @Override
  public void contextInitialized(ServletContextEvent sce) {
    System.out.println("allocate");
    try {
//      DBConnectionPool connectionPool = new DBConnectionPool(
//          "jdbc:mysql://db-ld250-kr.vpc-pub-cdb.ntruss.com/studydb",
//          "study", "bitcamp!@#123");
//
//      Map<String, Object> beanMap = new HashMap<>();
//      beanMap.put("connectionPool", connectionPool);

      ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
          "/config/application-context.xml");
//
//      beanMap.put("assignmentDao", new AssignmentDaoImpl(connectionPool));
//      beanMap.put("memberDao", new MemberDaoImpl(connectionPool));
//      beanMap.put("boardDao", new BoardDaoImpl(connectionPool));
//      beanMap.put("attachedFileDao", new AttachedFileDaoImpl(connectionPool));
//      beanMap.put("txManager", new TransactionManager(connectionPool));

      ServletContext webAppStorage = sce.getServletContext();

      webAppStorage.setAttribute("applicationContext", applicationContext);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public void contextDestroyed(ServletContextEvent sce) {
    System.out.println("deallocate");
  }
}
