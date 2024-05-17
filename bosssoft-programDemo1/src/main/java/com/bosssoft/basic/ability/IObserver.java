package com.bosssoft.basic.ability;

import com.bosssoft.exception.ServiceException;

import java.io.BufferedWriter;
import java.io.IOException;

/**
 * @description:
 * 观察者接口，FileTransferProgressObserver.java实现了这个接口
 * @author: LiuYang
 * @date: 2024/05/16 20:44
 **/
public interface IObserver {
    /**
     * @description:
     * 更新状态
     * @author: LiuYang
     * @date: 2024/05/17 19:07
     * @param subject
     * @param writer
     **/
    public void update(ISubject subject, BufferedWriter writer) throws IOException, ServiceException;
}