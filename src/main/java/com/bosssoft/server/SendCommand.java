/**
 * Copyright (C), 2001-2031, www.bosssof.com.cn
 * FileName: Server.java
 * Author: gry
 * Date: 2024/5/15 23:33
 * Description:
 * 发送指令
 * History:
 * Date          Author   Version  Desc
 * 2024-01-01    bosssoft  1.0.0   initialize this file
 */
package com.bosssoft.server;

import java.io.BufferedWriter;
import java.io.IOException;
import java.net.Socket;

/**
 * @className: SendCommand
 * @description:
 * @author: mint
 * @date: 2024/5/16 9:50
 * @since 1.0
 **/
public class SendCommand implements Command{
    private Socket clientSocket;
    private String filePath;

    public SendCommand(Socket clientSocket, String filePath) {
        this.clientSocket = clientSocket;
        this.filePath = filePath;
    }

    @Override
    public void execute() throws IOException {
        // 处理文件发送逻辑
        System.out.println("开始接收文件：" + filePath);
        // 文件接收逻辑（读取数据、解密、保存文件）
        // 假设已经完成接收并保存
        System.out.println("文件接收完成");
    }
}
