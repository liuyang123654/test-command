package com.bosssoft.basic.ability;

import java.nio.channels.Channel;

public interface IObserver {
    public void process (Channel channel, Object data);
}