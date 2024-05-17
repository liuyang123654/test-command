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

import com.bosssoft.entity.Code;
import com.bosssoft.exception.ExceptionHandler;
import com.bosssoft.exception.ServiceException;

import java.io.BufferedWriter;
import java.io.IOException;
import java.rmi.ServerException;

/**
 * @className: FileTransferProgressObserver
 * @description: 本类为文件传输过程观察者，观察的是FileTransferTask任务
 * @author: LiuYang
 * @date: 2024/5/15 16:22
 * @since 1.0
 **/
public class FileTransferProgressObserver implements IObserver {

    @Override
    /**
     * @description:
     * 观察者被通知到，符合条件的话就更新信息
     *
     * @author: LiuYang
     * @date: 2024/05/16 13:49
     * @param subject
     **/
    public void update(ISubject subject, BufferedWriter bufferedWriter) throws ServiceException, IOException {
        if (subject instanceof FileTransferTask) {
            FileTransferTask task = (FileTransferTask) subject;
            String status = task.getStatus();
            try {
                // 更新上传进度或显示状态信息
                bufferedWriter.write("File transfer status: " + status);
            } catch (IOException e) {
                //调用异常处理类
                ExceptionHandler.handleException(e);
                throw new ServiceException(Code.IOEXCEPTION,e);

            }finally {
                bufferedWriter.close();
            }
        }
    }
}
