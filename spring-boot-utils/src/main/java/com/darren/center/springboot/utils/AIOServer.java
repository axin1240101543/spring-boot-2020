package com.darren.center.springboot.utils;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

public class AIOServer {

    public static void main(String[] args) throws Exception{
        new AIOServer().getAccept();
        Thread.sleep(10000);
    }

    public void getAccept(){
        try(AsynchronousServerSocketChannel serverSocketChannel = AsynchronousServerSocketChannel
                .open()
                .bind(new InetSocketAddress(InetAddress.getLocalHost(), 8081))){
            //为异步操作指定CompletionHandler回调函数
            serverSocketChannel.accept(serverSocketChannel,
                    new CompletionHandler<AsynchronousSocketChannel, AsynchronousServerSocketChannel>() {
                @Override
                public void completed(AsynchronousSocketChannel result, AsynchronousServerSocketChannel attachment) {
                    //接收客户端连接
                    serverSocketChannel.accept(serverSocketChannel, this);
                    handleAccept(result);
                }

                @Override
                public void failed(Throwable exc, AsynchronousServerSocketChannel attachment) {
                    exc.printStackTrace();
                }
            });
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private void handleAccept(AsynchronousSocketChannel socketChannel){
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        long timeout = 10L;
        try {
            socketChannel.read(buffer, timeout, TimeUnit.SECONDS, null, new CompletionHandler<Integer, Object>() {
                @Override
                public void completed(Integer result, Object attachment) {
                    System.out.println("result:" + result);
                    if (-1 == result){
                        try {
                            socketChannel.close();
                        }catch (IOException ioe){
                            ioe.printStackTrace();
                        }
                        return;
                    }
                    //flip方法将Buffer从写模式切换到读模式。调用flip()方法会将position设回0，并将limit设置成之前position的值。
                    buffer.flip();
                    System.out.println("received message:" + Charset.defaultCharset().decode(buffer));
                    buffer.clear();
                    socketChannel.read(buffer, timeout, TimeUnit.SECONDS, null, this);
                }

                @Override
                public void failed(Throwable exc, Object attachment) {
                    exc.printStackTrace();
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
