package com.darren.center.springboot.io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.CountDownLatch;

/**
 * AIO server端简单实现
 *
 * AIO与NIO不同，当进行读写操作时，只需直接调用API的read或者write方法即可，
 * 这两种方法均为异步的。对于读操作而言，当有流可读取时，操作系统会将可读
 * 的流，传入到read方法的缓冲区，并通知应用程序。对于写操作而言，当操作系统
 * 将write方法传递的流写入完毕时，操作系统主动通知应用程序，即可以理解为，read
 * 或者write方法都是异步的，完成后会主动调用回调函数。
 *
 * AIO业务逻辑的关键在于，通过指定CompletionHandler的回调接口，在accept、read、write等关键节点，通过事件机制去调用。
 */
public class AIOPlainEchoServer {

    public void server(int port) throws IOException {
        System.out.println("Listening for connection on port " + port);
        //AsynchronousServerSocketChannel对应于NIO的ServerSocketChannel
        final AsynchronousServerSocketChannel serverChannel = AsynchronousServerSocketChannel.open();
        InetSocketAddress address = new InetSocketAddress(port);
        //将serverChannel绑定到指定的端口里
        serverChannel.bind(address);
        final CountDownLatch latch = new CountDownLatch(1);
        //开始接收新的客户端请求，一旦一个客户端请求被接收，CompletionHandler就会被调用
        serverChannel.accept(null, new CompletionHandler<AsynchronousSocketChannel, Object>() {
            /**
             * AsynchronousSocketChannel对应于NIO的SocketChannel
             * @param channel
             * @param attachment
             */
            @Override
            public void completed(final AsynchronousSocketChannel channel, Object attachment) {
                //一旦完成处理，再次接收新的客户端请求
                serverChannel.accept(null, this);
                ByteBuffer buffer = ByteBuffer.allocate(100);
                //在channel里植入一个读操作EchoCompletionHandler，一旦buffer有数据写入，EchoCompletionHandler便会被唤醒
                channel.read(buffer, buffer, new EchoCompletionHandler(channel));
            }

            @Override
            public void failed(Throwable exc, Object attachment) {
                try {
                    //若遇到异常，关闭channel
                    serverChannel.close();
                } catch (IOException e) {
                    //ignore on close
                }finally {
                    latch.countDown();
                }
            }
        });
        try {
            latch.await();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private final class EchoCompletionHandler implements CompletionHandler<Integer, ByteBuffer>{

        private final AsynchronousSocketChannel channel;

        public EchoCompletionHandler(AsynchronousSocketChannel channel) {
            this.channel = channel;
        }

        @Override
        public void completed(Integer result, ByteBuffer buffer) {
            buffer.flip();
            //在channel里植入一个读操作CompletionHandler，一旦channel有数据写入，CompletionHandler便会被唤醒
            channel.write(buffer, buffer, new CompletionHandler<Integer, ByteBuffer>() {
                @Override
                public void completed(Integer result, ByteBuffer buffer) {
                    if(buffer.hasRemaining()){
                        //如果buffer里还有内容，则再次出发写入操作将buffer里的内容写入channel
                        channel.write(buffer, buffer, this);
                    }else{
                        //将之前已经写入channel的内容给清除掉
                        buffer.compact();
                        //如果channel里还有内容需要读入到buffer里，则再次出发写入操作将channel里的内容读入buffer
                        channel.read(buffer, buffer, EchoCompletionHandler.this);
                    }
                }

                @Override
                public void failed(Throwable exc, ByteBuffer attachment) {
                    try {
                        channel.close();
                    } catch (IOException e) {
                        //ignore on close
                    }
                }
            });
        }

        @Override
        public void failed(Throwable exc, ByteBuffer attachment) {
            try {
                channel.close();
            } catch (IOException e) {
                //ignore on close
            }
        }
    }

}
