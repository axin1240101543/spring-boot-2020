package com.darren.center.springboot.utils;

import java.io.*;
import java.nio.channels.FileChannel;
import java.nio.file.Files;

/**
 * 文件拷贝
 */
public class FileCopy {

    private ThreadLocal<Long> startTime = new ThreadLocal<>();

    public static void main(String[] args) throws IOException{
        FileCopy copy = new FileCopy();
        File source = new File("C:\\Users\\Darren\\Desktop\\201905131108394799256511075.xls");
        File dest = new File("C:\\Users\\Darren\\Desktop\\copy.xls");
        copy.fileCopy2BIO(source, dest);
        copy.fileCopy2NIO(source, dest);
        Files.copy(source.toPath(), dest.toPath());
    }

    public void fileCopy2BIO(File source, File dest){
        long time = System.currentTimeMillis();
        startTime.set(time);
        try (InputStream inputStream = new FileInputStream(source);
             OutputStream outputStream = new FileOutputStream(dest)){
             byte[] buffer = new byte[1024];
             int length;
             while((length = inputStream.read()) > 0){
                 outputStream.write(buffer, 0, length);
             }
        }catch (IOException ioe){
            ioe.printStackTrace();
        }
        System.out.println("bio end：" + (System.currentTimeMillis() - startTime.get()));
    }

    public void fileCopy2NIO(File source, File dest){
        long time = System.currentTimeMillis();
        startTime.set(time);
        try (FileChannel sourceFileChannel = new FileInputStream(source).getChannel();
             FileChannel destFileChannel = new FileOutputStream(dest).getChannel()){
            for (long count = sourceFileChannel.size(); count > 0;){
                long transferred = sourceFileChannel.transferTo(0, count, destFileChannel);
                count -= transferred;
            }
        }catch (IOException ioe){
            ioe.printStackTrace();
        }
        System.out.println("nio end：" + (System.currentTimeMillis() - startTime.get()));
    }

}
