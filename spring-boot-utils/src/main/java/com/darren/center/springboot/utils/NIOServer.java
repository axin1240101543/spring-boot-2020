package com.darren.center.springboot.utils;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.util.Hashtable;
import java.util.Iterator;

public class NIOServer {

    public static void main(String[] args) {
        // 客户端列表
        Hashtable<String, SocketChannel> clientlist = new Hashtable<>();
        //创建Selector和Socket
        try (Selector selector = Selector.open();
             ServerSocketChannel server = ServerSocketChannel.open();) {
            server.configureBlocking(false);
            //注册到 Selector，并说明关注点
            server.register(selector, SelectionKey.OP_ACCEPT);
            // 启动端口监听
            server.socket().bind(new InetSocketAddress(InetAddress.getLocalHost(), 8080));
            while (true) {
                // 阻塞等待就绪的 Channel
                selector.select();
                Iterator<SelectionKey> it = selector.selectedKeys().iterator();
                while (it.hasNext()) {
                    SelectionKey key = it.next();
                    it.remove();
                    if (key.isAcceptable()) {
                        // 连接事件
                        ServerSocketChannel server2 = (ServerSocketChannel) key.channel();
                        SocketChannel channel = server2.accept();
                        channel.configureBlocking(false);
                        channel.register(selector, SelectionKey.OP_READ);
                        System.out.println("客户端连接：" + channel.socket().getInetAddress().getHostName() + ":" + channel.socket().getPort());
                    } else if (key.isReadable()) {
                        // 读取数据事件
                        SocketChannel channel = (SocketChannel) key.channel();
                        ByteBuffer buffer = ByteBuffer.allocate(1024);
                        channel.read(buffer);
                        buffer.flip();
                        String msg = Charset.forName("UTF-8").newDecoder().decode(buffer).toString();
                        System.out.println("收到：" + msg);
                        if (msg.startsWith("username=")) {
                            String username = msg.replaceAll("username=", "");
                            clientlist.put(username, channel);
                        } else {
                            // 转发消息给客户端
                            String[] arr = msg.split(":");
                            if (arr.length == 3) {
                                String from = arr[0];
                                String to = arr[1];
                                String content = arr[2];
                                if (clientlist.containsKey(to)) {
                                    CharsetEncoder encoder = Charset.forName("UTF-8").newEncoder();
                                    clientlist.get(to).write(encoder.encode(CharBuffer.wrap(from + ":" + content)));
                                }
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
