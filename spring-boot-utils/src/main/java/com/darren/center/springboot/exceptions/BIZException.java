package com.darren.center.springboot.exceptions;

import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

/**
 * 业务异常基类，所有业务异常都必须继承于此异常 定义异常时，需要先确定异常所属模块。例如： 添加用户报错 可以定义为 [10020001]
 * 前四位数为系统模块编号，后4位为错误代码 ,唯一 <br>
 * 用户中心 1002 <br>
 * 工具中心 1003 <br>
 */
@Getter
public class BIZException extends RuntimeException{

    public static final int DEFAULT_CODE = 10010001;
    public static final String DEFULAT_MSG = "系统错误";
    public static final int PARAMS_CHECK_ERROR = 10011001;

    /**
     * 具体异常码
     */
    protected int code;

    /**
     * 异常信息
     */
    protected String msg;

    public BIZException() {
        super();
    }

    public BIZException(String message) {
        super(message);
    }

    public BIZException(String message, Throwable cause) {
        super(message, cause);
    }

    public BIZException(Throwable cause) {
        super(cause);
    }

    public BIZException(int code, String msgFormat, Object ... args) {
        super(format(msgFormat, args));
        if (null != args && 0 == args.length && args[args.length-1] instanceof Throwable){
            super.initCause((Throwable)args[args.length-1]);
        }
        this.code = code;
        this.msg = format(msgFormat, args);
    }

    /**fix 异常信息包含错误的格式化信息,如%、%i等**/
    private static String format(String msgFormat, Object ... args){
        if (null == args || 0 == args.length){
            return msgFormat;
        }
        try {
            return String.format(msgFormat, args);
        }catch (Exception e){
            return msgFormat + StringUtils.join(args);
        }
    }

    /**
     * 实例化异常
     * @param msgFormat
     * @param args
     * @return
     */
    public BIZException newInstance(String msgFormat, Object ... args){
        return new BIZException(this.code, msgFormat, args);
    }

}
