package hello.servlet.webTest.frontcontroller.v3t;

import hello.servlet.webTest.frontcontroller.ModelView;
import hello.servlet.webTest.frontcontroller.MyView;
import hello.servlet.webTest.frontcontroller.v3t.controller.MemberFormControllerv3t;
import hello.servlet.webTest.frontcontroller.v3t.controller.MemberListControllerv3t;
import hello.servlet.webTest.frontcontroller.v3t.controller.MemberSaveControllerv3t;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name="frontControllerServletV3t", urlPatterns = "/front-controller/v3t/*")
public class FrontControllerServletv3t extends HttpServlet {

    private Map<String, ControllerV3t> controllerMap = new HashMap<>();

    public FrontControllerServletv3t() {
        controllerMap.put("/front-controller/v3t/members/new-form", new MemberFormControllerv3t());
        controllerMap.put("/front-controller/v3t/members/save", new MemberSaveControllerv3t());
        controllerMap.put("/front-controller/v3t/members", new MemberListControllerv3t());
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestURI = req.getRequestURI();
        System.out.println("requestURI = " + requestURI);

        ControllerV3t controller = controllerMap.get(requestURI);

        if (controller == null) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        Map<String, String> paramMap = new HashMap<>();
        req.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, req.getParameter(paramName)));
        ModelView mv = controller.process(paramMap);

        String viewPath = "/WEB-INF/views/" + mv.getViewName() + ".jsp";
        MyView view = new MyView(viewPath);
        view.render(mv.getParamMap(), req, resp);
    }
}
