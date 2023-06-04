package hello.exception.resolver;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Slf4j
public class MyHandlerExceptionResolver implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

        // ex, bad 둘 다 resolverException이 호출되긴 한다.
        // ex의 경우 if()에 안 맞아서 null반환되어 500이 되는 것.
        log.info("call MyHandlerExceptionResolver"); // error은 {}안해도 자동 프린트된다. trace처럼
        try {
            if (ex instanceof IllegalArgumentException) {
                // 원래 500인데 400으로 처리함.
                log.info("IllegalArgumentException resolver to 400");
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, ex.getMessage());
                return new ModelAndView();
                // 그냥 ModelAndView이므로 View에서 렌더링 되는 것 없이 WAS에는 정상 응답이 됨.
                // WAS에서는 response에 담긴 Error을 보고 처리
            }
        } catch (IOException e) {
            log.error("resolver ex", e);
        }
        return null; // exception이 처리되지 않고 그대로 WAS에 반환
    }
}
