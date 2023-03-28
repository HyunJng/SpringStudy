package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
// JDBC까지 연결한 통합테스트
@SpringBootTest
@Transactional // DB는 transaction 개념이 있다. commit하기 전까지는 반영X. 이걸 하면 test후 commit하지 않고 rollback하는 것.
class MemberServiceIntegrationTest {
    // 테스트는 한글로 적어도 됨.
    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

   /* DB에서 가져올 거라 그 전에 repository를 연결하여 Service에 넣어줄 필요가 없다.
   @BeforeEach
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }*/

    /* @Transaction이 있기 때문에 필요없다.
    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }
    */

    @Test
    void 회원가입() {
        //given
        Member member = new Member();
        member.setName("spring");

        //when
        Long saveId = memberService.join(member);

        //then 저장한게 repository에 저장된게 맞아?
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());

    }
    // 예외 터지는 것도 봐주어야한다.
    @Test
    public void 중복_회원_예외(){
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }
}