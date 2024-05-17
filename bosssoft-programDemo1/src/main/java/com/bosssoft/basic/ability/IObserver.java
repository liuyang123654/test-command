package com.bosssoft.basic.ability;

import java.io.BufferedWriter;
import java.io.IOException;

/**
 * @description:
 * @author: LiuYang
 * @date: 2024/05/16 20:44
 * @return: null
 **/
public interface IObserver {
    public void update(ISubject subject, BufferedWriter writer) throws IOException;
}