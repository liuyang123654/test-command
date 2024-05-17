/**
 * Copyright (C), 2001-2031, www.bosssof.com.cn
 * FileName: Server.java
 * Author: ShaoWen，LiuYang，gry
 * Date: 2024/5/15 23:33
 * Description:
 * Client客户端
 * History:
 * Date          Author   Version  Desc
 * 2024-01-01    bosssoft  1.0.0   initialize this file
 */
package com.bosssoft.client;

import com.bosssoft.basic.ability.SocketManager;
import com.bosssoft.exception.ExceptionHandler;
import com.bosssoft.exception.ServiceException;
import com.bosssoft.utils.EncryptAndDecryptUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @className: Client
 * @description: 客户端
 * @author: gry
 **/
public class Client {
    private static final String HOST = "localhost";
    private static final int PORT = 1234;

    public static void main(String[] args) throws IOException, InterruptedException {
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(2, 5, 2, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10));


        System.out.println("Connected to server");
        System.out.println("请输入命令:show + ' ' +  文件地址 / send + ID + 文件地址");
        try (SocketManager socketManager = new SocketManager(HOST, PORT)) {
            Scanner scanner = new Scanner(System.in);
            String newCommand = scanner.nextLine();
            String[] commands = newCommand.split(" ");
            if (commands[0].equals("show")) {
                // 请求查看文件内容
                showFileContent(threadPool, socketManager, commands[1]);
            } else if (commands[0].equals("send")) {
                // 发送文件
                sendFile(threadPool, socketManager, commands[1], commands[2]);

            } else {
                System.out.println("您的输入有误");
            }
            threadPool.shutdown();
            threadPool.awaitTermination(20, TimeUnit.SECONDS);
            System.out.println("Connection closed");
        } catch (Exception e) {
            ExceptionHandler.handleException(e);
            throw e;
        }
    }

    private static void sendFile(ThreadPoolExecutor threadPool, SocketManager socketManager, String receiver, String filePath) {
        threadPool.submit(() -> {
            try {
                //发送文件
                System.out.println("File transfer status: 开始接收文件...");

                //读取文件内容
                BufferedReader reader = new BufferedReader(new FileReader(filePath));
                String line;
                // 创建一个StringBuilder用于存储文件内容
                StringBuilder contentBuilder = new StringBuilder();
                while ((line = reader.readLine()) != null) {
                    // 将每行内容添加到StringBuilder中
                    contentBuilder.append(line).append("\n");
                }
                // 将StringBuilder转换为字符串
                String fileContent = contentBuilder.toString();
                //加密文件内容
                String encodedContent = EncryptAndDecryptUtils.encode(fileContent);
                //保存加密后的文件到临时位置
                String tempPath = generateTempFilePath(filePath);
                Files.write(Paths.get(tempPath), encodedContent.getBytes());
                String message = "send " + receiver+" " + tempPath;
                socketManager.send(message);
                String response = socketManager.receive();
                System.out.println(response);
                System.out.println("Sent file to server");
            } catch (IOException e) {
                //调用异常处理类
                ExceptionHandler.handleException(e);

            }
        });
    }

    private static void showFileContent(ThreadPoolExecutor threadPool, SocketManager socketManager, String filePath) {
        threadPool.submit(() -> {
            try {
                socketManager.send("show " + filePath);
                String response = socketManager.receive();
                System.out.println(response);
            } catch (IOException e) {
                //调用异常处理类
                ExceptionHandler.handleException(e);
            }
        });
    }
    /**
     * 临时文件路径
     * @param originalFilePath
     * @return
     */
    private static String generateTempFilePath(String originalFilePath) {
        File originalFile = new File(originalFilePath);
        String parentDirectory = originalFile.getParent(); // 获取原始文件的父目录
        String fileName = originalFile.getName(); // 获取原始文件的文件名
        String tempFileName = fileName.substring(0, fileName.lastIndexOf(".")) + "encoded" + fileName.substring(fileName.lastIndexOf("."));
        return parentDirectory + File.separator + tempFileName; // 构建临时文件路径
    }
}