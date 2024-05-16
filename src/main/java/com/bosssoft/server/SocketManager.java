/**
 * FileName: SocketManager.java
 * Author: ShaoWen
 * Date: 2024/5/16 9:40
 * Description:
 * 管理客户端
 */
package com.bosssoft.server;

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
        return reader.readLine();
    }

    @Override
    public void close() throws IOException {
        writer.close();
        reader.close();
        socket.close();
    }
}
