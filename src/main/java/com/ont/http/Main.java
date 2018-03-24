package com.ont.http;

public class Main {

    public static void main(String[] args) throws Exception{
        HttpServer httpServer = new SingleThreadHttpHttpServer();
        httpServer.run(8080);

    }
}
