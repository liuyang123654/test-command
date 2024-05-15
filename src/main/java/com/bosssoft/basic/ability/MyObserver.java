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

/**
 * @className: MyObserver
 * @description:观察者
 * @author: LiuYang
 * @date: 2024/5/15 16:22
 * @since 1.0
 **/
public class MyObserver implements IObserver {
    @Override
    public void process() {
        System.out.println("Observer执行动作...");
        System.out.println("变化已经被通知者观察到...");
    }
}
