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
package com.bosssoft.service;


import com.bosssoft.utils.FilePaserUtils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Writer;
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
    public void execute() throws IOException {
        //读取文件内容
        StringBuffer content=new StringBuffer();
        // 用带缓冲的流读取文件，默认缓冲区8k
        try (BufferedReader br = Files.newBufferedReader(Paths.get(filePath))){
            String line;
            while ((line = br.readLine()) != null) {
                content.append(line);
            }
        }

        //读取到文件内容后，调用文件解析工具,把文件内容解析
        Object obj=FilePaserUtils.parse(content.toString());

        // 读取文件内容并发送回客户端
        writer.write("文件内容显示：" + filePath);
        writer.write(obj.toString());
        writer.newLine();
        writer.flush();
    }


}
