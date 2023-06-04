package hello.exception.api;

import hello.exception.exception.UserException;
import hello.exception.exhandler.ErrorResult;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api2")
public class ApiExceptionV2Controller {

    // 이 컨트롤러에서 발생한 오류만 처리해줌.
    // ERROR 터지면 ExceptionResolver 그 중 ExceptionHandlerExceptionResolver이 호출됨
    // 이 게 Controller에 @ExceptionHanlder이 있는지 살펴보고 있으면 호출해 줌
    // 에러 처리해서 정상 흐름으로 바꾸어 반환해줌. => HTTP 상태 코드가 200이 되버림
    // => ResponseStatus를 붙여서 해결. => 정상 흐름인건 마찬가지
    // => 서블릿 컨테이너가 다시 요청 보내는 과정 사라짐.
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(IllegalArgumentException.class)
//    public ErrorResult illegalExHandler(IllegalArgumentException e) {
//        log.error("[exceptionHandler] ex", e);
//        return new ErrorResult("BAD", e.getMessage());
//    }
//
//    @ExceptionHandler
//    public ResponseEntity<ErrorResult> userExHandler(UserException e) {
//        log.error("[exceptionHandler] ex", e);
//        return ResponseEntity.badRequest().body(new ErrorResult("USER-EX", e.getMessage()));
//    }
//
//    // 정의되지 않은 에러는 여기서 공통으로 처리됨.
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) // 500
//    @ExceptionHandler
//    public ErrorResult exHandler(Exception e){
//        log.error("[exceptionHandler] ex", e);
//        return new ErrorResult("EX", "내부 오류");
//    }

    @GetMapping("/members/{id}")
    public MemberDto getMember(@PathVariable("id") String id) {
        if (id.equals("ex")) {
            throw new RuntimeException("잘못된 사용자");
        }

        if (id.equals("bad")) {
            throw new IllegalArgumentException("잘못된 입력 값");
        }

        if (id.equals("user-ex")) {
            throw new UserException("사용자 오류");
        }

        return new MemberDto((id), "hello " + id);
    }

    @Data
    @AllArgsConstructor
    static class MemberDto {
        private String memberId;
        private String name;
    }
}
