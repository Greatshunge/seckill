package org.seckill.exception;

/**
 * 秒杀关闭异常
 * Created by shunge on 2017/4/7.
 */
public class SeckillCloseException extends SeckillException{
    public SeckillCloseException() {
    }

    public SeckillCloseException(String message) {
        super(message);
    }

    public SeckillCloseException(String message, Throwable cause) {
        super(message, cause);
    }

    public SeckillCloseException(Throwable cause) {
        super(cause);
    }
}
