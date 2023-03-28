package hello.servlet.webTest.frontcontroller.v3t.controller;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import hello.servlet.webTest.frontcontroller.ModelView;
import hello.servlet.webTest.frontcontroller.v3t.ControllerV3t;

import java.util.List;
import java.util.Map;

public class MemberListControllerv3t implements ControllerV3t {

    MemberRepository memberRepository = MemberRepository.getInstance();
    @Override
    public ModelView process(Map<String, String> paramMap) {
        List<Member> members = memberRepository.findAll();

        ModelView mv = new ModelView("members");
        mv.getParamMap().put("members", members);
        return mv;
    }
}
