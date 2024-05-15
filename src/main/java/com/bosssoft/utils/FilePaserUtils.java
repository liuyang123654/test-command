/**
 * Copyright (C), 2001-2031, www.bosssoft.com.cn
 * FileName: FilePaserUtils.java
 * Author: LiuYang
 * Date: 2024/5/15 19:29
 * Description:
 * 解析文件工具
 * History:
 * Date          Author   Version  Desc
 * 2024-01-01    bosssoft  1.0.0   initialize this file
 */
package com.bosssoft.utils;


import com.alibaba.fastjson.JSON;
import jdk.internal.org.xml.sax.InputSource;
import jdk.internal.org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;
import java.util.Vector;

/**
 * @className: FilePaserUtils
 * @description:
 * 解析文件工具
 * @author: LiuYang
 * @date: 2024/5/15 19:29
 * @since 1.0
 **/
public class FilePaserUtils {
    /**
     * @description:
     * 获取文件字符串的类型，是json还是xml
     * @author: LiuYang
     * @date: 2024/05/15 20:19
     * @param string
     * @return: java.lang.String
     **/
    static String type=null;
    public static String getType(String string) {

            try {
                Object obj= JSON.parse(string);
                return "JSON";
            } catch (Exception e) {
                //log.info("string is not valid JSON.");
            }

            try {
                DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(String.valueOf(new InputSource(new StringReader(string))));
                return "XML";
            } catch (Exception e) {
                //log.info("Message is not valid XML.");
            }
            return null;
    }

    /**
     * @description:
     * 解析字符串，返回对象,xml或者json
     * @author: LiuYang
     * @date: 2024/05/15 20:22
     * @param string
     * @return: java.lang.Object
     **/
    public static Object parse(String string){
        if(type.equals("JSON")){
            Object obj= JSON.parse(string);
            return obj;
        }
        return null;
    }


}
