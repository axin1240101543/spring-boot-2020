package com.darren.center.springboot.io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * NIO server端简单实现
 *
 * 为什么这种方式比BIO更高效呢？
 * 利用了单线程的轮询事件机制，通过高效的定位就绪的channel，来决定做什么，
 * 仅仅select阶段是阻塞的，可以有效避免大量客户端连接时，频繁切换线程带来的问题，
 * 应用的扩展能力有了非常大的提高。
 */
public class NIOPlainEchoServer {

    public void server(int port) throws IOException {
        System.out.println("Listening for connection on port " + port);
        //通过ServerSocketChannel创建一个serverChannel
        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        //从serverChannel获取到serverSocket实例
        ServerSocket serverSocket = serverChannel.socket();
        InetSocketAddress address = new InetSocketAddress(port);
        //将serverSocket实例绑定到指定的端口里
        serverSocket.bind(address);
        //将serverChannel的阻塞状态设置为非阻塞状态
        //why?
        //因为阻塞模式下，注册操作是不允许的，会抛出java.nio.channels.IllegalBlockingModeException
        serverChannel.configureBlocking(false);
        System.out.println("serverChannel and serverSocket init finished !!!");
        //创建一个Selector，作为调度员的角色
        Selector selector = Selector.open();
        //然后将前面创建好的serverChannel注册到selector中，并说明让selector关注的点，这里是关注连接这个事件
        //通过指定SelectionKey.OP_ACCEPT告诉调度员，它关注的新的连接请求
        //每将一个channel注册到selector就会产生一个selectorKey
        serverChannel.register(selector, SelectionKey.OP_ACCEPT);
        while (true){
            try {
                //阻塞等待就绪的channel，即没有与客户端建立连接前就一直轮询
                //类似自旋
                selector.select();
            }catch (IOException e){
                e.printStackTrace();
                //代码省略的部分是结合业务，正确处理异常的逻辑
                break;
            }
        }
        //获取到selector里所有就绪的selectorKey实例
        Set<SelectionKey> readyKeys = selector.selectedKeys();
        Iterator<SelectionKey> iterator = readyKeys.iterator();
        while (iterator.hasNext()){
            SelectionKey key = iterator.next();
            //将就绪的selectionKey从selector中移除，因为马上就要去处理它，防止重复执行
            iterator.remove();
            try {
                //若selectorKey处于Acceptable（建立连接）状态
                if (key.isAcceptable()){
                    ServerSocketChannel server = (ServerSocketChannel) key.channel();
                    //接收客户端的连接
                    SocketChannel client = server.accept();
                    System.out.println("Accepted connection from " + client);
                    client.configureBlocking(false);
                    //向slector注册SocketChannel，主要关注读写，并传入一个ByteBuffer实例共读写缓存
                    client.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE, ByteBuffer.allocate(100));
                }
                //若selectorKey处于可读状态
                if(key.isReadable()){
                    SocketChannel client = (SocketChannel) key.channel();
                    ByteBuffer output = (ByteBuffer) key.attachment();
                    //从channel里读取数据存入到byteBuffer里面
                    client.read(output);
                }
                //若selectorKey处于可写状态
                if(key.isReadable()){
                    SocketChannel client = (SocketChannel) key.channel();
                    ByteBuffer output = (ByteBuffer) key.attachment();
                    //将ByteBuffer反转，之前是被写入，现在是从ByteBuffer给写出
                    output.flip();
                    //将ByteBuffer里的数据写入到channel里
                    client.write(output);
                    //将已经编写完成的数据从缓存中移除
                    output.compact();
                }
            }catch (IOException e){
                key.cancel();
                try {
                    key.channel().close();
                }catch (IOException ioe){
                }
            }
        }
    }

}
