/**
 * Copyright (C), 2001-2031, www.bosssoft.com.cn
 * FileName: ExceptionHandler.java
 * Author: LiuYang
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

/**
 * @className: ExceptionHandler
 * @description: 异常处理机制
 * @author: LiuYang
 * @date: 2024/5/15 17:12
 * @since 1.0
 **/
public class ExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(ExceptionHandler.class);

    /**
     * 如果抛出的的是ServiceException，则调用该方法
     * @param e 业务异常
     * @return Result
     */
    /**
     * @param e
     * @description: 自定义业务异常
     * @author: LiuYang
     * @date: 2024/05/15 17:19
     * @return: void
     **/
    public static void ServiceExceptionHandler(ServiceException e) {
        logger.error("错误码：", e.getCode(), ";发生业务异常！原因是：", e.getMessage());
        System.out.println("发生业务异常！原因是：{}" + e.getMessage());
    }


    /**
     * @param e
     * @description: 空指针异常
     * @author: LiuYang
     * @date: 2024/05/15 17:19
     * @return: void
     **/
    public void exceptionHandler(NullPointerException e) {
        logger.error("发生空指针异常！原因是:", e);
        System.out.println("发生空指针异常！原因是：{}" + e.getMessage());
    }

    /**
     * @param e
     * @description: 其他异常
     * @author: LiuYang
     * @date: 2024/05/15 17:18
     * @return: void
     **/
    public void exceptionHandler(Exception e) {
        logger.error("发生未知异常！原因是:", e);
        System.out.println("发生未知异常！原因是：{}" + e.getMessage());
    }

}
