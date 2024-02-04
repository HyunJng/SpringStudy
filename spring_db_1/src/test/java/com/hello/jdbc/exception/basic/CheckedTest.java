package com.hello.jdbc.exception.basic;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;


@Slf4j
public class CheckedTest {

    @Test
    public void checked_catch() {
        Service service = new Service();
        service.callCatch();
    }

    @Test
    public void checked_throw() {
        Service service = new Service();
        Assertions.assertThatThrownBy(() -> service.calllThrow())
                .isInstanceOf(MyCheckedException.class);
    }

    /**
     * Exception을 상속받은 예외는 체크 예외가 된다.
     */
    static class MyCheckedException extends Exception {
        public MyCheckedException(String message) {
            super(message);
        }
    }

    class Service {
        Repository repository = new Repository();

        /**
         * 예외를 잡아서 처리하는 코드
         */
        public void callCatch() {
            try {
                repository.call();
            } catch (MyCheckedException e) {
                // 예외 처리
                log.info("예외처리, message = {}", e.getMessage(), e);
            }
        }

        /**
         * 체크 예외를 밖으로 덙는 코드
         */
        public void calllThrow() throws MyCheckedException {
            repository.call();
        }
    }

    static class Repository {
        public void call() throws MyCheckedException {
            throw new MyCheckedException("ex");
        }
    }
}
