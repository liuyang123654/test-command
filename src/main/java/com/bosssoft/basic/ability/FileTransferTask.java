/**
 * Copyright (C), 2001-2031, www.bosssoft.com.cn
 * FileName: Subject.java
 * Author: LiuYang
 * Date: 2024/5/15 16:23
 * Description:
 * 主题类
 * History:
 * Date          Author   Version  Desc
 * 2024-01-01    bosssoft  1.0.0   initialize this file
 */
package com.bosssoft.basic.ability;

import java.util.ArrayList;
import java.util.List;

/**
 * @className: FileTransferTask
 * @description:这个类就是我们具体要观察的对象，即文件传输任务
 * @author: LiuYang
 * @date: 2024/5/15 16:23
 * @since 1.0
 **/
public class FileTransferTask implements ISubject{
    //观察者列表
    private List<IObserver> list = new ArrayList<>();
    // 文件传输状态
    private String status;


    /**
     * @description:注册观察者，观察者若想获得消息，必须先注册
     * @author: LiuYang
     * @date: 2024/05/15 16:38
     * @param observer
     **/
    @Override
    public void registerObserver(IObserver observer){
        list.add(observer);
    }

    /**
     * @description:广播消息
     * @author: LiuYang
     * @date: 2024/05/15 16:38
     **/
    @Override
    public void notifyObservers(){
        for (IObserver observer : list) {
            observer.update(this);
        }
    }
    /**
     * @description:
     * 更新主题状态数据的方法,一旦有更新，就通知所有观察者
     * @author: LiuYang
     * @date: 2024/05/15 16:52
     **/
    public void setStatus(String status) {
        this.status = status;
        notifyObservers(); // 状态变化时通知观察者
    }

    public String getStatus() {
        return status;
    }

    @Override
    public void removeObserver(IObserver observer) {
        list.remove(observer);
    }
}
