/**
 * FileName: Client.java
 * Author:boss2
 * Date: 2024/5/15 11:06
 * Description:
 * Client客户端app
 */
package com.bosssoft.client;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.*;

/**
 * @className: Client
 * @description:
 * @author:
 * @date: 2024/5/15 11:06
 * @since 1.0
 **/


public class Client {
    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor=new ThreadPoolExecutor(2,5,2,TimeUnit.SECONDS,new ArrayBlockingQueue<Runnable>(10));
        try {
            final Socket socket = new Socket("localhost", 12345);
            System.out.println("Connected to server");

            // 异步发送消息
            Future<Void> sendFuture = threadPoolExecutor.submit(new Callable<Void>() {
                @Override
                public Void call() throws Exception {
                    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                    String message = "Hello from client";
                    writer.write(message);
                    writer.newLine();
                    writer.flush();
                    System.out.println("Sent message to server: " + message);
                    return null;
                }
            });

            // 异步接收响应
            Future<String> receiveFuture = threadPoolExecutor.submit(new Callable<String>() {
                @Override
                public String call() throws Exception {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    String response = reader.readLine();
                    System.out.println("Received response from server: " + response);
                    return response;
                }
            });

            // 等待发送和接收完成
            sendFuture.get();
            String response = receiveFuture.get();

            // 关闭连接
            socket.close();
            System.out.println("Connection closed");
        } catch (IOException | InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }finally {
            //关闭线程池
            threadPoolExecutor.shutdown();
        }
    }
}
