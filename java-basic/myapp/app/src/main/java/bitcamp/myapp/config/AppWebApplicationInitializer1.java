package bitcamp.myapp.config;

import java.io.File;
import java.util.EnumSet;
import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

public class AppWebApplicationInitializer1 /*extends AbstractContextLoaderInitializer*/ {

  AnnotationConfigWebApplicationContext rootContext;

  //  @Override
  public void onStartup(ServletContext servletContext) throws ServletException {
//    super.onStartup(servletContext);

    AnnotationConfigWebApplicationContext appContext = new AnnotationConfigWebApplicationContext();
    appContext.register(AppConfig.class);
    appContext.setParent(rootContext);
    appContext.setServletContext(servletContext);
    appContext.refresh();

    DispatcherServlet dispatcherServlet = new DispatcherServlet(appContext);
    Dynamic registration = servletContext.addServlet("app", dispatcherServlet);
    registration.addMapping("/app/*");
    registration.setLoadOnStartup(1);
    registration.setMultipartConfig(new MultipartConfigElement(
        new File("./temp").getAbsolutePath(),
        1024 * 1024 * 10,
        1024 * 1024 * 100,
        1024 * 1024
    ));

    FilterRegistration.Dynamic filterRegistration = servletContext.addFilter(
        "CharacterEncodingFilter", new CharacterEncodingFilter("UTF-8"));
    filterRegistration.addMappingForServletNames(
        EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD, DispatcherType.INCLUDE),
        false,
        "app"
    );
  }

  //  @Override
  protected WebApplicationContext createRootApplicationContext() {
    rootContext = new AnnotationConfigWebApplicationContext();
    rootContext.register(RootConfig.class);
    rootContext.refresh();
    return rootContext;
  }
}