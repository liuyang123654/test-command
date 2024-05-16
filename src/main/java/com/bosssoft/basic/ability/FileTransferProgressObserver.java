/**
 * Copyright (C), 2001-2031, www.bosssoft.com.cn
 * FileName: MyObserver.java
 * Author: LiuYang
 * Date: 2024/5/15 16:22
 * Description:
 * 观察者类
 * History:
 * Date          Author   Version  Desc
 * 2024-01-01    bosssoft  1.0.0   initialize this file
 */
package com.bosssoft.basic.ability;

import java.nio.channels.Channel;

/**
 * @className: FileTransferProgressObserver
 * @description:
 * 本类为文件传输过程观察者，观察的是FileTransferTask任务
 * @author: LiuYang
 * @date: 2024/5/15 16:22
 * @since 1.0
 **/
public class FileTransferProgressObserver implements IObserver {

    @Override
    public void update(ISubject subject) {
        if (subject instanceof FileTransferTask) {
            FileTransferTask task = (FileTransferTask) subject;
            String status = task.getStatus();
            // 更新上传进度或显示状态信息
            System.out.println("File transfer status: " + status);
        }
    }
}
