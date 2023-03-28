package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")// 도메인 첫번째 들어오면 호출됨
    public String home(){
        return "home";
    }
}
