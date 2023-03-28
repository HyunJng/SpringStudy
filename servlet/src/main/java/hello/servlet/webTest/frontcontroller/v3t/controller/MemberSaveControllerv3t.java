package hello.servlet.webTest.frontcontroller.v3t.controller;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import hello.servlet.webTest.frontcontroller.ModelView;
import hello.servlet.webTest.frontcontroller.v3t.ControllerV3t;

import java.util.Map;

public class MemberSaveControllerv3t implements ControllerV3t {

    private MemberRepository memberRepository = MemberRepository.getInstance();
    @Override
    public ModelView process(Map<String, String> paramMap) {
        String username = paramMap.get("username");
        int age = Integer.parseInt(paramMap.get("age"));

        Member member = new Member(username, age);
        memberRepository.save(member);

        ModelView mv = new ModelView("save-result");
        mv.getParamMap().put("member", member);

        return mv;
    }
}
