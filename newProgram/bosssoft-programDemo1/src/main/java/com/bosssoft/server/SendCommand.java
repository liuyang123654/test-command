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
package com.bosssoft.server;

import com.bosssoft.basic.ability.FileTransferProgressObserver;
import com.bosssoft.basic.ability.FileTransferTask;
import com.bosssoft.exception.ExceptionHandler;
import com.bosssoft.utils.EncryptAndDecryptUtils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @className: SendCommand
 * @description: 解决什么问题：
 * send命令的执行
 * @author: gry

 **/
public class SendCommand implements Command{
    private BufferedWriter writer;
    private String filePath;

    public SendCommand(BufferedWriter writer, String filePath) {
        this.writer=writer;
        this.filePath = filePath;
    }

    @Override
    public void execute() throws IOException {
        /**
         * 读取文件内容后加密
         **/

        //读取文件内容
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        // 创建一个 StringBuilder 用于存储文件内容
        StringBuilder contentBuilder = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            // 将每行内容添加到 StringBuilder 中
            contentBuilder.append(line).append("\n");
        }
        // 将 StringBuilder 转换为字符串
        String fileContent = contentBuilder.toString();

        //加密文件内容
        String encodedContent = EncryptAndDecryptUtils.encode(fileContent);
        System.out.println(encodedContent);
        //解密后存储
        String decodedContent = EncryptAndDecryptUtils.decode(encodedContent);
        //保存解密后的文件到临时位置
        String tempPath = filePath;
        Files.write(Paths.get(tempPath), decodedContent.getBytes());
        /**
         * 通知观察者
         **/
        //文件传输任务（被观察对象）
        FileTransferTask fileTransferTask=new FileTransferTask();
        //注册观察者
        fileTransferTask.registerObserver(new FileTransferProgressObserver());

        //改变事件状态，让观察者被通知到
        StringBuffer stringBuffer=new StringBuffer("文件接收完成");
        stringBuffer.append('\n');
        // 内容后添加 "EOF" 标记表示文件结束
        stringBuffer.append("EOF").append("\n");

        fileTransferTask.setStatus(stringBuffer.toString(),writer);
        try{
            writer.newLine();
            writer.flush();//刷新流，保证都被提交
        }catch (IOException e){
            //调用异常处理类
            ExceptionHandler.handleException(e);
        }
    }

}

