package com.darren.center.springboot.classloader;

import java.io.*;

public class MyClassLoader extends ClassLoader{

    /**
     * .class文件的路径
     */
    private String path;

    /**
     * 类加载器的名字
     */
    private String classLoaderName;

    public MyClassLoader(String path, String classLoaderName) {
        this.path = path;
        this.classLoaderName = classLoaderName;
    }

    /**
     * 用于寻找类文件
     * @param name
     * @return
     */
    @Override
    public Class findClass(String name){
        //得到class文件流
        byte[] b = loadClassData(name);
        return defineClass(name, b, 0, b.length);
    }

    /**
     * 用于加载类文件
     * @param name
     * @return
     */
    private byte[] loadClassData(String name){
        name = path + name + ".class";
        InputStream in = null;
        ByteArrayOutputStream out = null;
        try{
            in = new FileInputStream(new File(name));
            out = new ByteArrayOutputStream();
            int i = 0;
            while ((i = in.read()) != -1){
                out.write(i);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            try {
                in.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return out.toByteArray();
    }
}
