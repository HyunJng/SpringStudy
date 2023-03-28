package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
// 서비스 메소드는 비즈니스에 가깝게 용어 선택해야함
@Transactional // JPA는 join들어올 때 transaction안에서 실행되어야한다.
public class MemberService {

    private final MemberRepository memberRepository;
    // 외부에서 넣어지도록
    @Autowired // 서비스를 스프링컨테이너에 등록하면서 컨테이너의 멤버리포지토리를 넣어줌
    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    // 회원 가입
    public long join(Member member){
/*
        Optional<Member> result = memberRepository.findByName(member.getName());
        result.ifPresent(m ->{
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });// 값이 있으면 동작. optional로 감쌌기 때문에 쓸 수 있따.
*/
        validateDuplicateMember(member); // 중복회원 검증

        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                        .ifPresent(m ->{
                            throw new IllegalStateException("이미 존재하는 회원입니다.");
                        });
    }

    // 전체 회원 조회
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
