/**
 * FileName: ClientFactory.java
 * Author: mint
 * Date: 2024/5/16 9:34
 * Description:
 * 客户端工厂类
 */
package com.bosssoft.client;

import com.bosssoft.server.SocketManager;

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