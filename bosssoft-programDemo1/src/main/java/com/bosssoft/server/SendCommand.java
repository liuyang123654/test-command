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

import java.io.*;
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
        //读取文件内容
        byte[] fileContent = Files.readAllBytes(Paths.get(filePath));
        //解密文件
        String decodedContent = EncryptAndDecryptUtils.decode(fileContent);
        //保存解密后的文件到临时位置
        String tempPath = generateTempFilePath(filePath);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempPath))) {
            // 将解密后的内容写入临时文件
            writer.write(decodedContent);
        }
        /**
         * 通知观察者
         **/
        //文件传输任务（被观察对象）
        FileTransferTask fileTransferTask=new FileTransferTask();
        //注册观察者
        fileTransferTask.registerObserver(new FileTransferProgressObserver());
        //改变事件状态，让观察者被通知到
        fileTransferTask.setStatus("文件接收完成",writer);
        try{
            writer.newLine();
            writer.flush();//刷新流，保证都被提交
        }catch (IOException e){
            //调用异常处理类
            ExceptionHandler.handleException(e);
        }
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
        String tempFileName = fileName.replace("encoded", "decoded");
        return parentDirectory + File.separator + tempFileName; // 构建临时文件路径
    }
}

