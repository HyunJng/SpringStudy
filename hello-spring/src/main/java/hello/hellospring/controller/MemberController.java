package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller // spring 컨테이너가 뜰 때 컨트롤러 객체 생성해서 관리함.
public class MemberController {
    // memberService를 가져다 써야함
    private final MemberService memberService;
    /* new 도 되지만 container에 등록하고 객체를 컨테이너에서 받아서 쓰도록 바꿔야함.
    다른 컨트롤러도 MemberService를 가져다 쓸 수 있으니까. 여러개 선언할 필요가 없이 하나만 등록하고
    같이 쓰는게 더 효율적.
    * */

    @Autowired // 스프링 컨테이너에 들어있는 MemberService와 연결해줌 => service, repository는 어노테이션 붙여주어서 인식하도록 만들어야함.
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMemberForm"; // template에서 찾음
    }
    @PostMapping("/members/new")
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());
        
        memberService.join(member);
                
        return "redirect:/"; // home화면으로 돌림
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
