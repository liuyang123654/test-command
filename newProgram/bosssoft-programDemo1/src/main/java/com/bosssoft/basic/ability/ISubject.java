/**
 * Copyright (C), 2001-2031, www.bosssoft.com.cn
 * FileName: ISubject.java
 * Author: LiuYang
 * Date: 2024/5/16 8:52
 * Description:
 * 观察的主题接口
 * History:
 * Date          Author   Version  Desc
 * 2024-01-01    bosssoft  1.0.0   initialize this file
 */
package com.bosssoft.basic.ability;

import java.io.BufferedWriter;
import java.io.IOException;

/**
 * @className: ISubject
 * @description:
 * @author: LiuYang
 * @date: 2024/5/16 8:52
 * @since 1.0
 **/
public interface ISubject {
    /**
     * @param observer
     * @description: 注册观察者
     * @author: LiuYang
     * @date: 2024/05/16 08:58
     **/
    void registerObserver(IObserver observer);

    /**
     * @param observer
     * @description: 移除观察者
     * @author: LiuYang
     * @date: 2024/05/16 08:59
     **/
    void removeObserver(IObserver observer);

    /**
     * @description: 通知观察者
     * @author: LiuYang
     * @date: 2024/05/16 08:59
     **/
    void notifyObservers(BufferedWriter bufferedWriter) throws IOException;
}