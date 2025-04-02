package com.inspire12.likelionbackend.module.core.aop.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(1) // order를 통해 순서 설정가능
public class UserInputValidateAspect {
    private static final Logger log = LoggerFactory.getLogger("UserInputValidateAspect");

    // 포인트 컷 위치 및 명칭 지정 (함수처럼 돌려쓰기 위함)
    @Pointcut("@annotation(com.inspire12.likelionbackend.module.core.aop.aspect.UserInputValidate)")
    public void userInputValidatePointcut() {}

    @Around("userInputValidatePointcut()")
    public Object userInputValidate(ProceedingJoinPoint joinPoint) throws Throwable {
        /* TODO username, email 두 input 값을 확인하는 코드와 함수 실행시점인 jointPoint.proceed() 넣어주세요 */
        Object[] args = joinPoint.getArgs();
//        String username = (String) args[0];
        String username = (args.length > 0 && args[0] instanceof String) ?  (String) args[0]: "unknown";
//        String email = (String) args[1];
        String email = (args.length > 0 && args[1] instanceof String) ?  (String) args[1]: "unknown";
        log.info("입력값 검증 시작");
        if (username == null || email == null) {
            throw new IllegalArgumentException("입력값이 비어 있습니다.");
        }
        // 이메일 검증
        if (!email.endsWith("@example.com")) {
            throw new SecurityException("허용되지 않은 이메일 도메인입니다.");
        }
        log.info("입력값 검증 완료");
        return joinPoint.proceed();
    }

//    @Before("execution(* com.inspire12.likelionbackend.module.core.aop.service.UserService.registerUser(String, String))")
//    public void userInputValidation(ProceedingJoinPoint joinPoint) throws Throwable {
//        Object[] args = joinPoint.getArgs();
////        String username = (String) args[0];
//        String username = (args.length > 0 && args[0] instanceof String) ?  (String) args[0]: "unknown";
////        String email = (String) args[1];
//        String email = (args.length > 0 && args[1] instanceof String) ?  (String) args[1]: "unknown";
//        if (username == null || email == null) {
//            throw new IllegalArgumentException("입력값이 비어 있습니다.");
//        }
//        // 이메일 검증
//        if (!email.endsWith("@example.com")) {
//            throw new SecurityException("허용되지 않은 이메일 도메인입니다.");
//        }
//    }
}