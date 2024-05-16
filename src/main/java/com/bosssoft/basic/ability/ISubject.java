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

/**
 * @className: ISubject
 * @description:
 * @author: LiuYang
 * @date: 2024/5/16 8:52
 * @since 1.0
 **/
public interface ISubject {
    /**
     * @description:
     * 注册观察者
     * @author: LiuYang
     * @date: 2024/05/16 08:58
     * @param observer
     **/
    void registerObserver(IObserver observer);

    /**
     * @description:
     * 移除观察者
     * @author: LiuYang
     * @date: 2024/05/16 08:59
     * @param observer
     **/
    void removeObserver(IObserver observer);
    /**
     * @description:
     * 通知观察者
     * @author: LiuYang
     * @date: 2024/05/16 08:59
     **/
    void notifyObservers();
}
