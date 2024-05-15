/**
 * FileName: Server.java
 * Author:boss2
 * Date: 2024/5/15 11:06
 * Description:
 * Server服务端app
 */
package com.bosssoft.server;

import sun.jvm.hotspot.runtime.Thread;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

/**
 * @className: Server
 * @description:
 * @author:
 * @date: 2024/5/15 11:06
 * @since 1.0
 **/

public class Server {
    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(20, 100, 2, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(100));
        try {
            ServerSocket serverSocket = new ServerSocket(11000);
            System.out.println("Server started, waiting for client...");

            while (true) {
                //旦接受到连接请求，就创建一个新的Socket对象。
                Socket socket = serverSocket.accept();
                System.out.println("Client connected: " + socket.getInetAddress().getHostAddress());
                threadPoolExecutor.submit(new ClientHandler(socket));

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //关闭线程池
            threadPoolExecutor.shutdown();
        }
    }

    private static class ClientHandler implements Runnable {
        private Socket socket;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

                String message = reader.readLine();
                System.out.println("Received message from client: " + message);

                // 处理消息（这里仅做回显）
                String response = "Server: " + message.toUpperCase();

                writer.write(response);
                writer.newLine();
                writer.flush();
                System.out.println("Sent response to client: " + response);

                // 关闭连接
                socket.close();
                System.out.println("Connection closed");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
