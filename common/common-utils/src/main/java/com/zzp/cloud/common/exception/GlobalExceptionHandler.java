package com.zzp.cloud.common.exception;

import com.zzp.cloud.common.dto.RespBean;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
/**
 * 全局异常操作
 * <p>
 *  //TODO
 *  GlobalExceptionHandler.java
 * </p>
 * @version v0.0.1
 * @author 佐斯特勒
 * @date 2020/7/28 13:53
 * @see  GlobalExceptionHandler
 **/
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(SQLException.class)
    public RespBean sqlException(SQLException e) {
        if (e instanceof SQLIntegrityConstraintViolationException) {
            return RespBean.error("该数据有关联数据，操作失败!");
        }
        return RespBean.error("数据库异常，操作失败!");
    }
}