package hello.core.order;

import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceImplTest {

    // 순수한 자바 테스트
    @Test
    void createOrder() {
        // set주입으로 만들면 자바코드인 createOrder만 테스트하고 싶어도
        // 빈을 주입하지 않으면 null 에러가 나기 때문에 가짜를 만들어 주입 먼저 해야한다. // createOrder에서 필드값을 사용하기 떄문
        // 즉 DI를 해주는 프레임워크 없이는 순수 자바코드 테스트가 불가능하다는 뜻
        MemoryMemberRepository memberRepository = new MemoryMemberRepository();
        memberRepository.save(new Member(1L, "name", Grade.VIP));

        // 그러니 생성자 주입을 사용하자.
        OrderServiceImpl orderService = new OrderServiceImpl(memberRepository, new FixDiscountPolicy());
        Order order = orderService.createOrder(1L, "itemA", 10000);
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }


}