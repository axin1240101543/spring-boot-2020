package com.darren.center.springboot.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * BIO server端简单实现
 */
public class BIOPlainEchoServer {

    /**
     * 潜在的问题？
     * java语言目前的线程是比较重量级的，启动或者销毁一个线程是有明显开销的，
     * 所以每处理一个请求，就启动一个线程开销比较大，那如何进行改良呢？
     * 此时应该引入线程池，来避免不必要的开销。
     *
     * @param port
     * @throws IOException
     */
    public void server(int port) throws IOException {
        //将ServerSocket绑定到指定的端口里
        final ServerSocket socket = new ServerSocket(port);
        while (true){
            //阻塞直到收到新的客户端连接
            final Socket clientSocket = socket.accept();
            System.out.println("Accepted connection from " + clientSocket);
            //创建一个子线程去处理客户端的请求
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try (BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                         PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true)){
                        //从客户端读取数据并原封不动回写回去
                        while (true){
                            writer.println(reader.readLine());
                            writer.flush();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }


    /**
     * 通过一个固定大小的线程池来负责管理工作线程能够避免频繁创建、销毁线程的开销，
     * 这是我们构建并发服务的典型方式，如果连接数并不是非常多，只有最多几百个连接
     * 的普通应用，这种模式往往非常有效。
     * 但是如果连接数量极具上升，很多请求就得不到响应了。
     * 此时，我们就应该想到引入了多路复用机制的NIO。
     * @param port
     * @throws IOException
     */
    public void improvedServer(int port) throws IOException {
        //将ServerSocket绑定到指定的端口里
        final ServerSocket socket = new ServerSocket(port);
        ExecutorService executorService =  Executors.newFixedThreadPool(6);
        while (true){
            //阻塞直到收到新的客户端连接
            final Socket clientSocket = socket.accept();
            System.out.println("Accepted connection from " + clientSocket);
            //将请求交给线程池去处理
            executorService.execute(() ->{
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                     PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true)){
                    //从客户端读取数据并原封不动回写回去
                    while (true){
                        writer.println(reader.readLine());
                        writer.flush();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }

}
