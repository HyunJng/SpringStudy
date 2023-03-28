package hello.core.autowired;

import hello.core.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

import java.util.Optional;

public class AutowiredTest {

    @Test
    void AutowiredOption() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);

    }

    static class TestBean {

        @Autowired(required = false) // 주입할 빈이 없으면 메서드 자체가 호출 X
        public void setNoBean1(Member noBean1) {
            System.out.println("noBean1 = " + noBean1);
        }

        @Autowired
        public void setNoBean2(@Nullable Member noBean2) { // 없어도 호출은 되는데 null을 주입
            System.out.println("noBean2 = " + noBean2);
        }
        @Autowired
        public void setNoBean3(Optional<Member> member3) { // 없으면 Optional.empty
            System.out.println("noBean3 = " + member3);
        }
    }

}
