package com.darren.center.springboot.exceptions;

public class UtilsException extends BIZException{

    public UtilsException() {
    }

    public UtilsException(String message) {
        super(message);
    }

    public UtilsException(String message, Throwable cause) {
        super(message, cause);
    }

    public UtilsException(Throwable cause) {
        super(cause);
    }

    public UtilsException(int code, String msg) {
        super(code, msg);
    }

    public UtilsException(int code, String msgFormat, Object... args) {
        super(code, msgFormat, args);
    }

    public static final int VERIFY_PARAM_ERROR = 10030002;
    public static final UtilsException UNKNOWN_EXCEPTION = new UtilsException(
            10030001, "系统错误");
}
