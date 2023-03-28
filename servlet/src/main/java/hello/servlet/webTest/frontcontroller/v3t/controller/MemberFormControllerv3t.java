package hello.servlet.webTest.frontcontroller.v3t.controller;

import hello.servlet.webTest.frontcontroller.ModelView;
import hello.servlet.webTest.frontcontroller.v3t.ControllerV3t;

import java.util.Map;

public class MemberFormControllerv3t implements ControllerV3t {

    @Override
    public ModelView process(Map<String, String> paramMap) {
        return new ModelView("new-form");
    }
}
