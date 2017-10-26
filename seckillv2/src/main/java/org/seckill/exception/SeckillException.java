package org.seckill.exception;

/**
 * 秒杀相关业务异常
 * Created by shunge on 2017/4/7.
 */
public class SeckillException extends RuntimeException{

    public SeckillException() {
    }

    public SeckillException(String message) {
        super(message);
    }

    public SeckillException(String message, Throwable cause) {
        super(message, cause);
    }

    public SeckillException(Throwable cause) {
        super(cause);
    }
}
