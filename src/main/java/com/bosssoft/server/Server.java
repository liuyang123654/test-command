/**
 * Copyright (C), 2001-2031, www.bosssof.com.cn
 * FileName: Server.java
 * Author: gry
 * Date: 2024/5/15 23:33
 * Description:
 * 0
 * History:
 * Date          Author   Version  Desc
 * 2024-01-01    bosssoft  1.0.0   initialize this file
 */
package com.bosssoft.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @className: Server
 * @description:
 * @author: gry
 * @date: 2024/5/15 11:06
 * @since 1.0
 **/
public class Server {
    private ServerSocket serverSocket;
    private ExecutorService pool;

    public Server(int port) throws IOException {
        serverSocket = new ServerSocket(12345);
        pool = new ThreadPoolExecutor(
                10,
                10,
                0L,
                TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<Runnable>(10)
        );
    }

    public void startServer() {
        System.out.println("Server started...");
        // 等待客户端连接
        try {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                pool.execute(new ClientHandler(clientSocket));
            }
        } catch (IOException e) {
            System.out.println("Server exception: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        int port = 12345; // Default port
        if (args.length > 0) {
            try {
                port = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                System.out.println("Invalid port number, using default.");
            }
        }

        try {
            Server server = new Server(port);
            server.startServer();
        } catch (IOException e) {
            System.err.println("Error starting the server: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private class ClientHandler implements Runnable {
        private Socket clientSocket;

        public ClientHandler(Socket socket) {
            this.clientSocket = socket;
        }

        @Override
        public void run() {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                 BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()))) {
                String inputLine;
                while ((inputLine = reader.readLine()) != null) {
                    String[] commands = inputLine.split(" ");
                    switch (commands[0]) {
                        case "send":
                            new SendCommand(clientSocket, commands[1]).execute();
                            break;
                        case "show":
                            new ShowCommand(writer, commands[1]).execute();
                            break;
                        default:
                            writer.write("未知命令");
                            writer.newLine();
                            writer.flush();
                    }
                }
            } catch (IOException e) {
                System.out.println("处理客户端数据时出错：" + e.getMessage());
            }
        }
    }
}

//
//public class Server {
//    public static void main(String[] args) {
//        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(20, 100, 2, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(100));
//        try {
//            ServerSocket serverSocket = new ServerSocket(11000);
//            System.out.println("Server started, waiting for client...");
//
//            while (true) {
//                //旦接受到连接请求，就创建一个新的Socket对象。
//                Socket socket = serverSocket.accept();
//                System.out.println("Client connected: " + socket.getInetAddress().getHostAddress());
//                threadPoolExecutor.submit(new ClientHandler(socket));
//
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            //关闭线程池
//            threadPoolExecutor.shutdown();
//        }
//    }
//
//    private static class ClientHandler implements Runnable {
//        private Socket socket;
//
//        public ClientHandler(Socket socket) {
//            this.socket = socket;
//        }
//
//        @Override
//        public void run() {
//            try {
//                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
//
//                String message = reader.readLine();
//                System.out.println("Received message from client: " + message);
//
//                // 处理消息（这里仅做回显）
//                String response = "Server: " + message.toUpperCase();
//
//                writer.write(response);
//                writer.newLine();
//                writer.flush();
//                System.out.println("Sent response to client: " + response);
//
//                // 关闭连接
//                socket.close();
//                System.out.println("Connection closed");
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//}
