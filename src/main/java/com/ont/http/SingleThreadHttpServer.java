package com.ont.http;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class SingleThreadHttpServer implements HttpServer {

    public void run(int port) throws IOException {
        ServerSocket serverSocket = new ServerSocket(port);
        try {
            while (true) {
                Socket socket = serverSocket.accept();

                BufferedReader inBufferReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                StringBuilder stringBuffer = new StringBuilder();

                String inputLine;
                while (!(inputLine = inBufferReader.readLine()).equals("")) {
                    stringBuffer.append(inputLine);
                    stringBuffer.append("\r\n");
                }

                System.out.println(stringBuffer.toString());

                OutputStream outStream = socket.getOutputStream();
                BufferedReader bufferedReader = new BufferedReader(new StringReader("A Message from server."));

                // Header should be ended with '\r\n' at each line.
                outStream.write("HTTP/1.0 200 OK\r\n".getBytes());
                outStream.write("Main: OneServer 0.1\r\n".getBytes());
                outStream.write("Content-length: 22\r\n".getBytes()); // if text/plain the length is required
                outStream.write("Content-Type: text/plain\r\n".getBytes());

                // An empty line is required after the header
                outStream.write("\r\n".getBytes());

                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    outStream.write(line.getBytes());
                }

                inBufferReader.close();
                bufferedReader.close();
                outStream.flush();
                outStream.close(); // Socket will close automatically once output stream is closed.
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
