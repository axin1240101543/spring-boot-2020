package com.darren.center.springboot.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.util.Iterator;

public class NIOClient extends Thread {

    private CharsetDecoder decoder = Charset.forName("UTF-8").newDecoder();
    private CharsetEncoder encoder = Charset.forName("UTF-8").newEncoder();
    private Selector selector = null;
    private SocketChannel socket = null;
    private SelectionKey clientKey = null;
    private String username;

    public static void main(String[] args) {
        String username = "Darren";
        NIOClient client = new NIOClient(username);
        client.start();
        BufferedReader sin = new BufferedReader(new InputStreamReader(System.in));
        try {
            String line;
            while ((line = sin.readLine()) != null) {
                if (line.equals("bye")) {
                    client.close();
                    System.exit(0);
                }
                client.send(username + ":" + line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    /**
     * 启动客户端
     *
     * @param username
     */
    public NIOClient(String username) {
        try {
            selector = Selector.open();
            socket = SocketChannel.open();
            socket.configureBlocking(false);
            clientKey = socket.register(selector, SelectionKey.OP_CONNECT);
            socket.connect(new InetSocketAddress(InetAddress.getLocalHost(), 8080));
            this.username = username;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            while (true) {
                // 阻塞等待就绪的 Channel
                selector.select();
                Iterator<SelectionKey> it = selector.selectedKeys().iterator();
                while (it.hasNext()) {
                    SelectionKey key = it.next();
                    it.remove();
                    if (key.isConnectable()) {
                        // 连接事件
                        SocketChannel channel = (SocketChannel) key.channel();
                        if (channel.isConnectionPending()) {
                            channel.finishConnect();
                        }
                        channel.register(selector, SelectionKey.OP_READ);
                        System.out.println("连接服务器端成功！");
                        this.send("username=" + this.username);
                    } else if (key.isReadable()) {
                        // 读取数据事件
                        SocketChannel channel = (SocketChannel) key.channel();
                        ByteBuffer buffer = ByteBuffer.allocate(1024);
                        channel.read(buffer);
                        buffer.flip();
                        String msg = decoder.decode(buffer).toString();
                        System.out.println("收到：" + msg);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close();
        }
    }

    /**
     * 发送msg
     *
     * @param msg
     */
    public void send(String msg) {
        try {
            SocketChannel client = (SocketChannel) clientKey.channel();
            client.write(encoder.encode(CharBuffer.wrap(msg)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 关闭客户端
     */
    public void close() {
        try {
            selector.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
