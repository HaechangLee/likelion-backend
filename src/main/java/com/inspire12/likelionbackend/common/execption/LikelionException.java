package com.inspire12.likelionbackend.common.execption;

import lombok.Getter;

/*
1. 예외처리 클래스로 처리 ==> 클래스 정의시 extends Exception 처리
*/
@Getter
public class LikelionException extends RuntimeException{
    // TODO
    private final ErrorCode errorCode;

    public LikelionException() {
        super("Likelion error");
        this.errorCode = ErrorCode.LIKELION_ERROR;
    }
}
