/**
 * Copyright (C), 2001-2031, www.bosssof.com.cn
 * FileName: Server.java
 * Author: gry
 * Date: 2024/5/15 23:33
 * Description:
 * 管理客户端
 * History:
 * Date          Author   Version  Desc
 * 2024-01-01    bosssoft  1.0.0   initialize this file
 */
package com.bosssoft.basic.ability;

import java.io.*;
import java.net.Socket;

/**
 * @className: SocketManager
 * @description:
 * @author: ShaoWen
 * @date: 2024/5/16 9:40
 * @since 1.0
 **/
public class SocketManager implements Closeable {
    private Socket socket;
    private BufferedWriter writer;
    private BufferedReader reader;

    public SocketManager(String host, int port) throws IOException {
        this.socket = new Socket(host, port);
        this.writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    public void send(String data) throws IOException {
        writer.write(data);
        writer.newLine();
        writer.flush();
    }

    public String receive() throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        String line;

        while (!(line = reader.readLine()).equals("EOF")) {
            stringBuilder.append(line).append("\n");
        }
        return stringBuilder.toString();
    }

    @Override
    public void close() throws IOException {
        writer.close();
        reader.close();
        socket.close();
    }
}
