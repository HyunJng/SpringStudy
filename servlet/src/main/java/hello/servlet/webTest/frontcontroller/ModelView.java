package hello.servlet.webTest.frontcontroller;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter @Setter
public class ModelView {
    private String viewName;
    private Map<String, Object> paramMap = new HashMap<>();

    public ModelView(String viewName) {
        this.viewName = viewName;
    }
}
