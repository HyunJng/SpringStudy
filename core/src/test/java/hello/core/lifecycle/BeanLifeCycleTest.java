package hello.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {

    @Test
    public void lifeCycleTest(){
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        NetworkClient client = ac.getBean(NetworkClient.class);
        ac.close(); // 잘 안써서 ApplicationContext가 아니라 ConfigurableApplicationContext를 ㅅ써야함. Annotation보다 위에 있음
    }

    @Configuration
    static class LifeCycleConfig{

        @Bean //(initMethod = "init", destroyMethod = "close")
        public NetworkClient networkClient(){
            NetworkClient networkClient = new NetworkClient(); // 처음엔 url=null로 시작
            networkClient.setUrl("http://hello-spring.dev");
            return networkClient;
        }
    }
}
