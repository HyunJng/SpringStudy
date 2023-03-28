package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach // 메소드가 끝날 때마다 실행
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save(){ // MemoryMemberRepository의 save기능이 잘 되는지 test
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();  //get()은 optional벗기는 용도
        //결과가 맞는지 아닌지 확인
        // Assertions.assertThat(member).isEqualTo(result); // alt+enter -> addImportstatic하면 객체 없이 바로 쓸수 있게 됨
        assertThat(member).isEqualTo(result); // alt+enter -> addImportstatic하면 객체 없이 바로 쓸수 있게 됨
    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();  // 복사하고 shift + f6
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();
        assertThat(result).isEqualTo(member1);

    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll(); // 모든 멤버 list로 반환

        assertThat(result.size()).isEqualTo(2); // 두 개 넣어졌겠지?
    }

    /* 모든 테스트는 순서가 바뀔 수 있음
    테스트가 끝나면 데이터를 clear하게 해주어야한다.
    *
    * */

}
