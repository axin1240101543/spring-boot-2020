package com.darren.center.springboot.throwable;

import java.io.FileNotFoundException;

/**
 * 对比Error和Exception
 */
public class ErrorAndException {

    private void throwError(){
        throw new StackOverflowError();
    }

    private void throwRuntimeException(){
        throw new RuntimeException();
    }

    /**
     * 如果不知道如何处理，直接往外抛即可，不要吞下异常，也不要抛出父类异常，即抛出IO异常
     * @throws FileNotFoundException
     */
    private void throwCheckedException() throws FileNotFoundException {
        throw new FileNotFoundException();
    }

    public static void main(String[] args) throws FileNotFoundException {
        ErrorAndException eae = new ErrorAndException();
        //eae.throwError();
        //eae.throwRuntimeException();
        eae.throwCheckedException();
    }
}
