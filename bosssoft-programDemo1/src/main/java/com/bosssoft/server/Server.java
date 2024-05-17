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

import com.bosssoft.exception.ExceptionHandler;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

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
        serverSocket = new ServerSocket(1234);
        pool = Executors.newFixedThreadPool(10);
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
            //调用统一异常处理类
            ExceptionHandler.handleException(e);
        }
    }

    public static void main(String[] args) {
        int port = 12345; // Default port
        if (args.length > 0) {
            try {
                port = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                ExceptionHandler.handleException(e);
                System.out.println("Invalid port number, using default.");
            }
        }

        try {
            Server server = new Server(port);
            server.startServer();
        } catch (IOException e) {
            System.err.println("Error starting the server: " + e.getMessage());
            ExceptionHandler.handleException(e);

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
                            new SendCommand(writer, commands[2]).execute();
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
                //调用统一异常处理类
                ExceptionHandler.handleException(e);
                System.out.println("处理客户端数据时出错：" + e.getMessage());
            }
        }
    }
}