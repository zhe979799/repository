package org.zhe.common.s_common.item.advice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.zhe.common.s_common.item.enums.ExceptionEnum;
import org.zhe.common.s_common.item.exception.ShopException;
import org.zhe.common.s_common.item.vo.ExceptionResult;

import java.util.Enumeration;

@ControllerAdvice
public class CommonExceptionHandler {

    @ExceptionHandler(ShopException.class) //需要处理的异常类型
    public ResponseEntity<ExceptionResult> handlerException(ShopException s){

        return ResponseEntity.status(s.getExceptionEnum().getCode())
                .body(new ExceptionResult(s.getExceptionEnum()));
    }

}
