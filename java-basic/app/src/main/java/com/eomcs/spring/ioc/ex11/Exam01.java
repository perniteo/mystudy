// 클래스를 이용하여 스프링 설정하기 - @ComponentScan 사용법
package com.eomcs.spring.ioc.ex11;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.eomcs.spring.ioc.SpringUtils;

public class Exam01 {

  public static void main(String[] args) {
    ApplicationContext iocContainer = //
        new AnnotationConfigApplicationContext(AppConfig.class);

    String car2 = iocContainer.getBean("car2").toString();
    String engine2 = iocContainer.getBean("engine2").toString();
    System.out.println(car2);
    System.out.println(engine2);


    SpringUtils.printBeanList(iocContainer);

  }

}


