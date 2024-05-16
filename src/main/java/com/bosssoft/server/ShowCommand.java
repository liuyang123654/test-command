/**
 * Copyright (C), 2001-2031, www.bosssof.com.cn
 * FileName: ShowCommand.java
 * Author: gry
 * Date: 2024/5/16 8:36
 * Description:
 * 0
 * History:
 * Date          Author   Version  Desc
 * 2024-01-01    bosssoft  1.0.0   initialize this file
 */
package com.bosssoft.server;

import com.bosssoft.utils.FilePaserUtils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @className: ShowCommand
 * @description: 解决什么问题：
 * show命令的执行
 * @author: gry
 **/
public class ShowCommand implements Command {
    private BufferedWriter writer;
    private String filePath;

    public ShowCommand(BufferedWriter writer, String filePath) {
        this.writer = writer;
        this.filePath = filePath;
    }

    @Override
    public void execute() {
        try {
            // 确保文件存在
            File file = new File(filePath);
            if (!file.exists()) {
                writer.write("文件不存在：" + filePath);
                writer.newLine();
                writer.flush();
                return;
            }
            // 读取文件内容并发送回客户端
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            StringBuilder contentBuilder = new StringBuilder(); // 创建一个 StringBuilder 用于存储文件内容
            while ((line = reader.readLine()) != null) {
                contentBuilder.append(line).append("\n"); // 将每行内容添加到 StringBuilder 中
            }
            contentBuilder.append("EOF").append("\n"); // 在文件内容后添加 "EOF" 标记表示文件结束

            String fileContent = contentBuilder.toString(); // 将 StringBuilder 转换为字符串

            writer.write(fileContent); // 将整个文件内容写入输出流
            writer.flush(); // 确保所有内容都被发送

        } catch (IOException e) {
            try {
                writer.write("读取文件时发生错误：" + e.getMessage());
                writer.newLine();
                writer.flush();
            } catch (IOException ex) {
                System.err.println("无法写入客户端：" + ex.getMessage());
            }
        }
    }


}
