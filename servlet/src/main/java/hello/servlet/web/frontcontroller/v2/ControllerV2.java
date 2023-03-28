package hello.servlet.web.frontcontroller.v2;

import hello.servlet.web.frontcontroller.MyView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface ControllerV2 {
    // 기존 반환형은 void로, 각 Controller에서 직접 dispatcher 생성해서 render했었음
    // 이번은 MyView에서 몰아서 할 것
    MyView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
