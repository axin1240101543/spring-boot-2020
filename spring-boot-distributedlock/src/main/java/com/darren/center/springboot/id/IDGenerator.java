package com.darren.center.springboot.id;

import java.util.List;

/**
 * ID生成接口
 */
public interface IDGenerator {

    /**
     * 适用于自增ID
     * @param key
     * @return
     */
    public ID next(String key);

    /**
     * 按照指定格式生成自增编号
     * @param key
     * @param format
     * @return
     */
    public ID next(String key, String format);

    /**
     * 如现有逻辑不满足需求，可重新实现IDHandler类
     * @param key
     * @param idFormat
     * @return
     */
    public ID next(String key, IDFormat idFormat);

    /**
     *  根据key生成序列，并且这个序列有超时时间
     * @param key
     * @param idFormat
     * @param timeout
     * @return
     */
    public ID next(String key, IDFormat idFormat, int timeout);

    public List<Object> getInstanceKeys();

}
