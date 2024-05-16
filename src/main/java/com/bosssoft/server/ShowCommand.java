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

import java.io.BufferedWriter;
import java.io.IOException;

/**
 * @className: ShowCommand
 * @description:
 * @author: gry
 * @date: 2024/5/16 9:53
 * @since 1.0
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
        // 处理文件显示逻辑
        // 读取文件内容并发送回客户端
        writer.write("文件内容显示：" + filePath);
        writer.newLine();
        writer.flush();
    }
}
