/**
 * Copyright (C), 2001-2031, www.bosssoft.com.cn
 * FileName: EncryptAndDecreptUtils.java
 * Author: LiuYang
 * Date: 2024/5/15 19:20
 * Description:
 * 加解密文件类
 * History:
 * Date          Author   Version  Desc
 * 2024-01-01    bosssoft  1.0.0   initialize this file
 */
package com.bosssoft.utils;
import com.bosssoft.entity.Code;
import com.bosssoft.exception.ExceptionHandler;
import com.bosssoft.exception.ServiceException;
import org.springframework.util.Base64Utils;

import java.io.UnsupportedEncodingException;

/**
 * @className: EncryptAndDecreptUtils
 * @description:
 * base64加解密工具类
 * @author: LiuYang
 * @date: 2024/5/15 19:20
 * @since 1.0
 **/
public class EncryptAndDecryptUtils {

    /**
     * @description:
     * 加密方法
     * @author: LiuYang
     * @date: 2024/05/15 20:04
     * @param str
     * @return: java.lang.String
     **/
    public static String encode(String str){
        return new String(Base64Utils.encode(str.getBytes()));
    }

    /**
     * @description:
     * 解密方法
     * @author: LiuYang
     * @date: 2024/05/15 20:04
     * @param bytes
     * @return: java.lang.String
     **/
    public static String decode(byte[] bytes) {
        try {
            return new String(Base64Utils.decode(bytes), "utf8");
        } catch (UnsupportedEncodingException e) {
            ExceptionHandler.handleException(e);
            return "解码异常";
        }
    }

}
