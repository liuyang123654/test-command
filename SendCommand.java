/**
 * Copyright (C), 2001-2031, www.bosssof.com.cn
 * FileName: SendCommand.java
 * Author: gry
 * Date: 2024/5/16 8:34
 * Description:
 * 0
 * History:
 * Date          Author   Version  Desc
 * 2024-01-01    bosssoft  1.0.0   initialize this file
 */
package com.bosssoft.service;

import com.bosssoft.basic.ability.FileTransferProgressObserver;
import com.bosssoft.basic.ability.FileTransferTask;

import java.io.IOException;
import java.net.Socket;

/**
 * @className: SendCommand
 * @description: 解决什么问题：
 * send命令的执行
 * @author: gry

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
        //文件传输任务（被观察对象）
        FileTransferTask fileTransferTask=new FileTransferTask();
        //注册观察者
        fileTransferTask.registerObserver(new FileTransferProgressObserver());
        //改变事件状态，让观察者被通知到
        fileTransferTask.setStatus("开始接收文件");
        // 处理文件发送逻辑


        // 文件接收逻辑（读取数据、解密、保存文件）
        // 假设已经完成接收并保存
        //改变事件状态，让观察者被通知到
        fileTransferTask.setStatus("文件接收完成");
    }
}
