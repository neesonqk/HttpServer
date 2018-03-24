package com.ont.http;

import java.io.IOException;

public interface HttpServer {
    void run(int port) throws IOException;
}
