package bitcamp.myapp.config;

import java.io.File;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class AppWebApplicationInitializer implements WebApplicationInitializer {

  @Override
  public void onStartup(ServletContext servletContext) throws ServletException {
    AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
    rootContext.register(RootConfig.class);
    rootContext.setServletContext(servletContext);
    rootContext.refresh();

    ContextLoaderListener contextLoaderListener = new ContextLoaderListener(rootContext);
    servletContext.addListener(contextLoaderListener);

    AnnotationConfigWebApplicationContext appContext = new AnnotationConfigWebApplicationContext();
    appContext.register(AppConfig.class);
    appContext.setParent(rootContext);
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

//    FilterRegistration.Dynamic filterRegistration = servletContext.addFilter(
//        "CharacterEncodingFilter", new CharacterEncodingFilter("UTF-8"));
//    filterRegistration.addMappingForServletNames(
//        EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD, DispatcherType.INCLUDE),
//        false,
//        "app"
//    );
  }
}