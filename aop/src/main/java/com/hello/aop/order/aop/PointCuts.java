package com.hello.aop.order.aop;

import org.aspectj.lang.annotation.Pointcut;

public class PointCuts {
    // com.hello.order 패키지와 하위패키지
    @Pointcut("execution(* com.hello.aop.order..*(..))")
    public void allOrder(){} // pointcut signiture

    // 클래스 이름 패턴이 *Service인 것
    @Pointcut("execution(* *..*Service.*(..))")
    public void allService(){}

    // allOrder && allService
    @Pointcut("allOrder() && allService()")
    public void orderAndService(){}
}
