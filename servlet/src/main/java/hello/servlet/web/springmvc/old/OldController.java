package hello.servlet.web.springmvc.old;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component("/springmvc/old-controller") // springbean의 이름을 url패턴으로 맞췄음
public class OldController implements Controller {

    // @Controller 해야하는 것 아닌가요? => 아님. 핸들러매핑 처리를 해주어야함
    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("OldController.handlerRequest");
        return new ModelAndView("new-form");
    }
}
