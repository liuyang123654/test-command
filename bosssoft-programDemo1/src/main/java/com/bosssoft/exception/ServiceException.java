/**
 * Copyright (C), 2001-2031, www.bosssoft.com.cn
 * FileName: ServiceException.java
 * Author: LiuYang
 * Date: 2024/5/15 17:14
 * Description:
 * 自定义异常类
 * History:
 * Date          Author   Version  Desc
 * 2024-01-01    bosssoft  1.0.0   initialize this file
 */
package com.bosssoft.exception;

/**
 * @className: ServiceException
 * @description:
 * 自定义业务异常
 * @author: LiuYang
 * @date: 2024/5/15 17:14
 * @since 1.0
 **/
public class ServiceException extends Exception{

    //自定义错误码
    private int code;
    public ServiceException(int code,String msg){
        super(msg);
        this.code=code;
    }

    public int getCode() {
        return code;
    }
}
