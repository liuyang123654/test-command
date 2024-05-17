package com.bosssoft.entity;

/**
 * @description:
 * 自定义错误码
 * @author: ShaoWen
 * @date: 2024/05/17 17:29
 **/
public class Code {

    /**
     * 不支持编码异常
     **/
    public static final int UNSUPPORTED_ENCODING_EXCEPTION= 111;

    /**
     * 空指针异常
     **/
    public static final int NULL_POINTER_EXCEPTION= 222;

    /**
     * 自定义业务异常
     **/
    public static final int SERVICE_EXCEPTION= 333;

    /**
     * IO异常
     **/
    public static final int IOEXCEPTION= 444;

    /**
     * 未知异常
     **/
    public static final int EXCEPTION= 555;
}
