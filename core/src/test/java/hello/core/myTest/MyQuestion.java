package hello.core.myTest;

import hello.core.AutoAppConfig;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MyQuestion {
    @Test
    void AutoAppConfig안의빈확인하기() {
//        ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);
//        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
//        for (String beanDefinitionName : beanDefinitionNames) {
//            Object bean = ac.getBean(beanDefinitionName);
//            System.out.println("bean이름: " + beanDefinitionName + " bean = " + bean);
//        }
    }
}
