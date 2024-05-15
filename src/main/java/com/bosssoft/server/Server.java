/**
 * FileName: Server.java
 * Author:boss2
 * Date: 2024/5/15 11:06
 * Description:
 * Server服务端app
 */
package com.bosssoft.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @className: Server
 * @description:
 * @author:
 * @date: 2024/5/15 11:06
 * @since 1.0
 **/

public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(12345);
            System.out.println("Server started, waiting for client...");

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Client connected: " + socket.getInetAddress().getHostAddress());

                Thread thread = new Thread(new ClientHandler(socket));
                thread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
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
