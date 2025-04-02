package com.inspire12.likelionbackend.module.core.aop.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class.getName());

    public void registerUser(String username, String email) {

        long start = System.currentTimeMillis();
        // 로깅
        logStartExecutionTime(username);
        // 유효성 검사
        validateUserInput(username, email);
        // 핵심 로직 (실행했다 치고)
        log.info("DB에 사용자 저장 로직 실행");

        long end = System.currentTimeMillis();
        // 로깅
        logEndExecutionTime(username, end-start);
    }

    private void logStartExecutionTime(String username) {
        // TODO registerUser 에서 로깅을 담당하는 파트를 담는다
        log.info("사용자 등록 시작 - username: {}", username);
    }

    private void logEndExecutionTime(String username, long end) {
        // TODO registerUser 에서 로깅을 담당하는 파트를 담는다
        log.info("사용자 등록 완료 - username: {} 걸린 시간: {}", username, end);

    }
    private void validateUserInput(String username, String email) {
        // TODO registerUser 에서 유저 입력 유효성 체크를 담당하는 파트를 담는다
        // 유효성 검사
        if (username == null || email == null) {
            throw new IllegalArgumentException("입력값이 비어 있습니다.");
        }
        // 이메일 검증
        if (!email.endsWith("@example.com")) {
            throw new SecurityException("허용되지 않은 이메일 도메인입니다.");
        }
    }

}
