package com.darren.center.springboot.utils;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;

public class AIOClient {

    public static void main(String[] args) throws Exception{
        AsynchronousSocketChannel client = AsynchronousSocketChannel.open();
        client.connect(new InetSocketAddress(InetAddress.getLocalHost(), 8081)).get();
        client.write(ByteBuffer.wrap("Hello World!!!".getBytes()));
        Thread.sleep(100000);
    }
}
