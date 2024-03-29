// BeanPostProcessor 사용법 - 동작 원리
package spring.ioc.ex08.c1;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.eomcs.spring.ioc.SpringUtils;

public class Exam01 {

  public static void main(String[] args) throws BeansException {
    ApplicationContext iocContainer = new ClassPathXmlApplicationContext(//
        "com/eomcs/spring/ioc/ex08/c1/application-context.xml");

    // Spring IoC 컨테이너에 들어 있는 객체 출력
    SpringUtils.printBeanList(iocContainer);

    // Car 객체를 꺼내 Engine 객체가 주입되었는지 확인해보자!
    System.out.println(iocContainer.getBean("c1"));

  }

}


