/**
 * Copyright (C), 2001-2031, www.bosssof.com.cn
 * FileName: Server.java
 * Author: ShaoWen
 * Date: 2024/5/15 23:33
 * Description:
 * Client客户端工厂类
 * History:
 * Date          Author   Version  Desc
 * 2024-01-01    bosssoft  1.0.0   initialize this file
 */
package com.bosssoft.client;

import com.bosssoft.basic.ability.SocketManager;

import java.io.IOException;

/**
 * @className: ClientFactory
 * @description:
 * @author: ShaoWen
 * @date: 2024/5/16 9:34
 * @since 1.0
 **/
public class ClientFactory {
    public static ClientInterface createClient(String host, int port) {
        try {
            SocketManager socketManager = new SocketManager(host, port);
            return new Client(socketManager);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}