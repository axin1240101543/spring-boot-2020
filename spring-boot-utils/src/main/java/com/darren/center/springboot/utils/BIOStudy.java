package com.darren.center.springboot.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

public class BIOStudy extends Thread{

    private int localPort;

    public static void main(String[] args){
        BIOStudy server = new BIOStudy();
        server.start();
        try (Socket client = new Socket(InetAddress.getLocalHost(), server.getPort());
             BufferedReader bufferedReader = new BufferedReader(
                     new InputStreamReader(client.getInputStream()))){
            //读取服务器msg
            bufferedReader.lines().forEach(s -> System.out.println(s));
        }catch (IOException ioe){
            ioe.printStackTrace();
        }
    }

    @Override
    public void run() {
        try (ServerSocket serverSocket = new ServerSocket(0)){
            localPort = serverSocket.getLocalPort();
            ExecutorService executor = new ThreadPoolExecutor(5, 20, 60L,
                    TimeUnit.SECONDS, new ArrayBlockingQueue<>(10));
            while (true){
                Socket socket = serverSocket.accept();
                executor.execute(() -> {
                    RequestHandler requestHandler = new RequestHandler(socket);
                    requestHandler.start();
                });
            }
        }catch (IOException ioe){
            ioe.printStackTrace();
        }
    }

    public int getPort(){
        return localPort;
    }

    /**
     * 给客户端发送msg
     */
    class RequestHandler extends Thread{

        private Socket socket;

        RequestHandler(Socket socket){
            this.socket = socket;
        }

        @Override
        public void run() {
            try (PrintWriter printWriter = new PrintWriter(socket.getOutputStream())){
                 printWriter.println("Ok, accept your request");
                 printWriter.flush();
            }catch (IOException ioe){
                ioe.printStackTrace();
            }
        }
    }
}
