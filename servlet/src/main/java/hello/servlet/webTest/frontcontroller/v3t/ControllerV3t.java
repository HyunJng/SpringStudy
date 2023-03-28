package hello.servlet.webTest.frontcontroller.v3t;

import hello.servlet.webTest.frontcontroller.ModelView;

import java.util.Map;

public interface ControllerV3t {

    public ModelView process(Map<String, String> paramMap);
}
