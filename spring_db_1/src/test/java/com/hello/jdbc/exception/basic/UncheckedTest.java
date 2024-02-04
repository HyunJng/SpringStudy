package com.hello.jdbc.exception.basic;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

@Slf4j
public class UncheckedTest {

    /**
     * RuntimeException을 상속받은 예외는 언체크 예외가 된다.
     * */
    static class MyUncheckedException extends RuntimeException {
        public MyUncheckedException(String message) {
            super(message);
        }
    }
    @Test
    public void checked_catch() {
        UncheckedTest.Service service = new UncheckedTest.Service();
        service.callCatch();
    }

    @Test
    public void checked_throw() {
        UncheckedTest.Service service = new UncheckedTest.Service();
        Assertions.assertThatThrownBy(() -> service.calllThrow())
                .isInstanceOf(UncheckedTest.MyUncheckedException.class);
    }

    /**
     * UnChecked
     * 예외는 예외를 잡거나, 던지지 않아도 된다.
     * 예외를 잡지 않으면 자동으로 밖으로 던진다.
     * */
    static class Service {
        Repository repository = new Repository();

        public void callCatch() {
            try {
                repository.call();
            } catch (MyUncheckedException e) {
                log.info("예외처리 = {}", e.getMessage(), e);
            }
        }

        /**
         * 예외를 잡지 않아도 된다. 상위로 던져진다.
         * */
        public void calllThrow() {
            repository.call();
        }
    }
    static class Repository {
        public void call() {
            throw new MyUncheckedException("ex");
        }
    }

}
