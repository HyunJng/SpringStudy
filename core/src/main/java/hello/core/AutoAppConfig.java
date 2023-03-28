package hello.core;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan( // @Component를 찾아서 자동으로 빈으로 생성해준다. ex) Configuration
        // basePackage : 이 위치에서부터 시작해서 하위패키지를 scan함
        //basePackages = "hello.core.member",
        // 지정된 클래스의 패키지를 시작 위치로 지정한다.
        basePackageClasses = AutoAppConfig.class,
        // 컴포턴트 스캔할 때 뺄 것 지정. Configuration어노테이션 붙은거(AppConfig.class) 뻄
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {
/* @Component으로 빈 생성 되어있는데 수동으로 등록해서 오류남
    @Bean(name = "memoryMemberRepository")
    MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
    */
}
