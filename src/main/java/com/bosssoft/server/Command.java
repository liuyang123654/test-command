/**
 * Copyright (C), 2001-2031, www.bosssof.com.cn
 * FileName: Command.java
 * Author: gry
 * Date: 2024/5/15 23:33
 * Description:
 * 指令接口
 * History:
 * Date          Author   Version  Desc
 * 2024-01-01    bosssoft  1.0.0   initialize this file
 */
package com.bosssoft.server;

import java.io.IOException;
/**
 * @className: Command
 * @description:
 * @author: gry
 * @date: 2024/5/16 9:50
 * @since 1.0
 **/
public interface Command {
    void execute() throws IOException;
}
