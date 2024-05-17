/**
 * Copyright (C), 2001-2031, www.bosssoft.com.cn
 * FileName: ExceptionHandler.java
 * Author: LiuYang,ShaoWen
 * Date: 2024/5/15 17:12
 * Description:
 * 异常处理机制
 * History:
 * Date          Author   Version  Desc
 * 2024-01-01    bosssoft  1.0.0   initialize this file
 */
package com.bosssoft.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @className: ExceptionHandler
 * @description: 异常处理机制
 * @author:
 * @date: 2024/5/15 17:12
 * @since 1.0
 **/
public class ExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(ExceptionHandler.class);

    /**
     * 通用异常处理方法
     *
     * @param e 异常
     */
    public static void handleException(Throwable e) {
        if (e instanceof ServiceException) {
            handleServiceException((ServiceException) e);
        } else if (e instanceof NullPointerException) {
            handleNullPointerException((NullPointerException) e);
        } else if (e instanceof IOException) {
            handleIOException((IOException) e);
        } else {
            handleException((Exception) e);
        }
    }

    /**
     * 处理业务异常
     *
     * @param e 业务异常
     */
    private static void handleServiceException(ServiceException e) {
        logger.error("错误码：{}; 发生业务异常！原因是：{}", e.getCode(), e.getMessage());
    }

    /**
     * 处理空指针异常
     *
     * @param e 空指针异常
     */
    private static void handleNullPointerException(NullPointerException e) {
        logger.error("发生空指针异常！原因是：{}", e.getMessage(), e);
    }

    /**
     * 处理IO异常
     *
     * @param e IO异常
     */
    private static void handleIOException(IOException e) {
        logger.error("发生IO异常！原因是：{}", e.getMessage(), e);
    }

    /**
     * 处理其他异常
     *
     * @param e 其他异常
     */
    private static void handleException(Exception e) {
        logger.error("发生未知异常！原因是：{}", e.getMessage(), e);
    }

}
