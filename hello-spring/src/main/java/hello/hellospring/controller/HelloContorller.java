package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloContorller {

    @GetMapping("hello") /*get방식으로 /hello 넘어오면 실행 */
    public String hello(Model model){
        model.addAttribute( "data", "hello!!");
        return "hello"; /*resources>templates에서 hello 파일 찾아서 전달*/
    }
    /* MVC설명 */
    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(value="name") String name, Model model){ /*외부에서 parameter 받아옴*/
        model.addAttribute("name", name);
        return "hello-template";
    }
    /* API 설명 1 */
    @GetMapping("hello-string")
    @ResponseBody //http에서 Body에 이 데이터를 내가 직접 넣어주겠다는 뜻. view 없음
    public String helloString(@RequestParam("name") String name){
        return "hello "+name;
    }

    /* API 설명 2*/
    @GetMapping("hello-api")
    @ResponseBody     // method
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello; // json방식으로 객체가 전달됨
    }
    // 객체
    static class Hello{
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
