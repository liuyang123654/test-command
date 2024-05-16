package com.bosssoft.server;

import java.io.IOException;

public interface Command {
    void execute() throws IOException;
}
